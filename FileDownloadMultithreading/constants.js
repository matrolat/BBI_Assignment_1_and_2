
// constants.js


export const files = {
    file1: { name: "File 1", url: "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4" },
    file2: { name: "File 2", url: "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4" },
    file3: { name: "File 3", url: "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4" },
    file4: { name: "File 4", url: "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/VolkswagenGTIReview.mp4" },
  };

export const ERROR_MESSAGES = {
    OFFLINE_START: "You are offline. Please connect to the internet to start the download.",
    DOWNLOAD_ERROR: (fileName, error) => `Error downloading ${fileName}: ${error}`,
    OFFLINE_PAUSE: "You are offline. Downloads have been paused.",
    ONLINE_RESUME: "You are back online. Downloads have resumed.",
};

export const NetworkStatus = Object.freeze({
    ONLINE: "online",
    OFFLINE: "offline",
  });
  
  
  // enum for environment status
export const ENV = Object.freeze({
    DEVELOPMENT: "dev",
    PRODUCTION: "prod"
});
  


export const ActionType = Object.freeze({
    PAUSE: "pause",
    RESUME: "resume",
    START: "start"
});