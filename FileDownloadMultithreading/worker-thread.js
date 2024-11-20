let paused = false;
let receivedLength = 0;
let chunks = [];
let abortController = null;
let downloadUrl = "";
let fileId = "";

onmessage = async (e) => {
  const { action, url, id } = e.data;
  if (action === "pause") {
    paused = true;
    if (abortController) abortController.abort();
    return;
  }

  if (action === "resume") {
    if (paused && downloadUrl && fileId) {
      paused = false;
      await downloadFile(downloadUrl, fileId);
    }
    return;
  }

  if (action === "start") {
    paused = false;
    receivedLength = 0;
    chunks = [];
    downloadUrl = url;
    fileId = id;
    await downloadFile(downloadUrl, fileId);
  }
};

async function downloadFile(url, id) {
  try {
    abortController = new AbortController();

    const headers =
      receivedLength > 0 ? { Range: `bytes=${receivedLength}-` } : {};

    const response = await fetch(url, {
      signal: abortController.signal,
      headers,
    });

    if (!response.ok) {
      self.postMessage({ type: "error", error: response.statusText, id });
      return;
    }

    const contentRange = response.headers.get("Content-Range");
    const contentLength = contentRange
      ? parseInt(contentRange.split("/")[1], 10)
      : parseInt(response.headers.get("Content-Length")) + receivedLength;

    const reader = response.body.getReader();

    while (true) {
      if (paused) return;

      const { done, value } = await reader.read();
      if (done) break;

      chunks.push(value);
      receivedLength += value.length;

      const progress = Math.round((receivedLength / contentLength) * 100);
      self.postMessage({ type: "progress", progress, id });
    }

    const blob = new Blob(chunks, { type: "audio/mp4" });
    self.postMessage({ type: "completed", blob, id });
  } catch (error) {
    if (!paused) {
      self.postMessage({ type: "error", error: error.message, id });
    }
  }
}
