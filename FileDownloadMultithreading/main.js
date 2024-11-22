// main.js
import { ERROR_MESSAGES } from './constants.js';
import { log } from './utils/logger.js';
import { files } from './constants.js';


const workers = {};
const pausedFiles = new Set();

Object.keys(files).forEach((fileId) => {
  const { name, url } = files[fileId];

  const startButton = document.getElementById(`start-${fileId}`);
  const pauseButton = document.getElementById(`pause-${fileId}`);
  const progressBar = document.getElementById(`progress-${fileId}`);

  const worker = new Worker("worker-thread.js");
  workers[fileId] = worker;
  let isPaused = false;

  startButton.addEventListener("click", () => {
    if (!navigator.onLine) {
      alert(ERROR_MESSAGES.OFFLINE_START);
      return;
    }
    log(`Starting download for ${name} (${fileId}).`);
    worker.postMessage({ action: "start", url, id: fileId });
    startButton.disabled = true;
    pauseButton.disabled = false;
    pauseButton.textContent = "Pause";
  });

  pauseButton.addEventListener("click", () => {
    isPaused = !isPaused;

    const progressBar = document.getElementById(`progress-${fileId}`);
    progressBar.style.backgroundColor = "#2980b9";

    log(`${isPaused ? "Pausing" : "Resuming"} download for ${name} (${fileId}).`);
    worker.postMessage({ action: isPaused ? "pause" : "resume", id: fileId });
    pauseButton.textContent = isPaused ? "Resume" : "Pause";
  });

  worker.onmessage = (e) => {
    const { type, progress, id, blob, error } = e.data;
    
    const progressTextElement = document.getElementById(`progress-text-${id}`);
    console.log(progress+" "+ id);
    
    if (type === "progress" && id === fileId) {
      log(`Download progress for ${name} (${fileId}): ${progress}%`);
      progressBar.style.width = `${progress}%`;
      progressTextElement.textContent = `${progress}%`;
    } else if (type === "completed" && id === fileId) {
      log(`Download completed for ${name} (${fileId}).`);
      const blobUrl = URL.createObjectURL(blob);
      const link = document.createElement("a");
      link.href = blobUrl;
      link.download = name;
      link.textContent = "Download File";
      document.getElementById(`download-${fileId}`).appendChild(link);
      pauseButton.disabled = true;
      worker.terminate();
    } else if (type === "error" && id === fileId) {
      log(`Error downloading ${name} (${fileId}): ${error}`);
      alert(ERROR_MESSAGES.DOWNLOAD_ERROR(name, error));
      worker.terminate();
    }
  };
});

window.addEventListener("online", () => {

  
  log("Network status: Online. Resuming downloads.");
  pausedFiles.forEach((fileId) => {
    workers[fileId].postMessage({ action: "resume", id: fileId });
    const pauseButton = document.getElementById(`pause-${fileId}`);
    const progressBar = document.getElementById(`progress-${fileId}`);
    progressBar.style.backgroundColor = "#2980b9";
    

    pauseButton.textContent = "Pause";
  });
  pausedFiles.clear();
  alert(ERROR_MESSAGES.ONLINE_RESUME);
});

window.addEventListener("offline", () => {
  log("Network status: Offline. Pausing downloads.");

  Object.keys(workers).forEach((fileId) => {
    workers[fileId].postMessage({ action: "pause", id: fileId });
    pausedFiles.add(fileId);

    

    const progressBar = document.getElementById(`progress-${fileId}`);
    progressBar.style.backgroundColor = "grey";

    const pauseButton = document.getElementById(`pause-${fileId}`);
    pauseButton.textContent = "Resume";
  });
  alert(ERROR_MESSAGES.OFFLINE_PAUSE);
});
