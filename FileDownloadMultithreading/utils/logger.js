// logger.js
import { CONFIG } from "../config.js";

export const log = (message, ...optionalParams) => {
  const env = CONFIG.ENV || "production"; // Default to production if not set
  if (env === "dev") {
    console.log(message, ...optionalParams);
  }
};
