const files = {
    file1: { name: "File 1", url: "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4" },
    file2: { name: "File 2", url: "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4" },
    file3: { name: "File 3", url: "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4" },
    file4: { name: "File 4", url: "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/VolkswagenGTIReview.mp4" },
  };
  
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
        alert("You are offline. Please connect to the internet to start the download.");
        return;
      }
      worker.postMessage({ action: "start", url, id: fileId });
      startButton.disabled = true;
      pauseButton.disabled = false;
      pauseButton.textContent = "Pause";
    });
  
    pauseButton.addEventListener("click", () => {
      isPaused = !isPaused;
      worker.postMessage({ action: isPaused ? "pause" : "resume", id: fileId });
      pauseButton.textContent = isPaused ? "Resume" : "Pause";
    });
  
  

    worker.onmessage = (e) => {
        const { type, progress, id, blob, error } = e.data;
      
        const progressTextElement = document.getElementById(`progress-text-${id}`); 
      
        if (type === "progress" && id === fileId) {
         
          progressBar.style.width = `${progress}%`;
          progressTextElement.textContent = `${progress}%`;
        } else if (type === "completed" && id === fileId) {
          const blobUrl = URL.createObjectURL(blob);
          const link = document.createElement("a");
          link.href = blobUrl;
          link.download = name;
          link.textContent = "Download File";
          document.getElementById(`download-${fileId}`).appendChild(link);
          pauseButton.disabled = true;
          worker.terminate();
        } else if (type === "error" && id === fileId) {
          alert(`Error downloading ${name}: ${error}`);
          worker.terminate();
        }
      };
      



  });
  

  window.addEventListener("online", () => {
    pausedFiles.forEach((fileId) => {
      workers[fileId].postMessage({ action: "resume", id: fileId });
      const pauseButton = document.getElementById(`pause-${fileId}`);
      pauseButton.textContent = "Pause";
    });
    pausedFiles.clear();
    alert("You are back online. Downloads have resumed.");
  });
  
  window.addEventListener("offline", () => {
    Object.keys(workers).forEach((fileId) => {
      workers[fileId].postMessage({ action: "pause", id: fileId });
      pausedFiles.add(fileId);
      const pauseButton = document.getElementById(`pause-${fileId}`);
      pauseButton.textContent = "Resume";
    });
    alert("You are offline. Downloads have been paused.");
  });
  