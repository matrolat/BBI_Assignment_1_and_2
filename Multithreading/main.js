let workerPool = [];
let workerCount =  Math.min(navigator.hardwareConcurrency , 4);
let startTime, endTime;
let PrimeLimit = 1000000;
// let PrimeLimit = 100000000;

const statusEl = document.getElementById('status');
const resultEl = document.getElementById('result');
const executionTimeEl = document.getElementById('executionTime');
const cancelBtn = document.getElementById('cancelBtn');
const workerCountSlider = document.getElementById('workerCountSlider');
const workerCountValue = document.getElementById('workerCountValue');

workerCountSlider.addEventListener('input', () => {
    workerCount = parseInt(workerCountSlider.value);
    workerCountValue.textContent = workerCount;
});

document.getElementById('singleThreadBtn').addEventListener('click', () => {
    document.getElementById('progressContainer').style.display = 'block';
    updateProgress(0);

    statusEl.textContent = 'Status: Running (Single-Threaded)';
    cancelBtn.disabled = true;
    startTime = performance.now();
    const result = calculatePrimes(PrimeLimit);
    endTime = performance.now();
    document.getElementById('progressContainer').style.display = 'none';
    console.log("Single Threaded Total Time:" + (endTime - startTime).toFixed(2) + " ms");
    
    displayResult(result, 'Single-Threaded');
});

document.getElementById('multiThreadBtn').addEventListener('click', () => {
    statusEl.textContent = 'Status: Running (Multi-Threaded)';
    cancelBtn.disabled = false;
    startTime = performance.now();
    runMultiThreaded(PrimeLimit);
});

cancelBtn.addEventListener('click', () => {
    terminateWorkers();
    statusEl.textContent = 'Status: Operation Cancelled';
    cancelBtn.disabled = true;
});

function calculatePrimes(max) {
    const primes = [];
    for (let i = 2; i <= max; i++) {
        if (isPrime(i)) {
        // console.log(i);
          
          primes.push(i);
        }

    }
    return primes;
}

function isPrime(num) {
    for (let i = 2, sqrt = Math.sqrt(num); i <= sqrt; i++) {
        if (num % i === 0) return false;
    }
    return num > 1;
}

function runMultiThreaded(max) {
    console.log("Multi-threaded execution:");
    
    const range = Math.ceil(max / workerCount);
    let completed = 0;
    let primes = [];

    document.getElementById('progressContainer').style.display = 'block';
    updateProgress(0);

    terminateWorkers();  
    workerPool = Array.from({ length: workerCount }, (_, index) => {
        const worker = new Worker('worker-thread.js');

        worker.postMessage({ start: index * range + 1, end: (index + 1) * range });

        worker.onmessage = (e) => {
            primes = primes.concat(e.data);
            completed++;
            const progress = Math.round((completed / workerCount) * 100);
            updateProgress(progress);
            console.log("Multie Threaded Worker " + (index+1) +" Time:" + (performance.now() - startTime).toFixed(2) + " ms");

            if (completed === workerCount) {
                endTime = performance.now();
                displayResult(primes, 'Multi-Threaded');
                terminateWorkers();
                document.getElementById('progressContainer').style.display = 'none';
            }
        };

        worker.onerror = (e) => {
            console.error('Worker error:', e.message);
            terminateWorkers();
        };

        return worker;
    });
}

function updateProgress(percentage) {
    const circle = document.querySelector('.circle');
    const radius = circle.getAttribute('r');
    const circumference = 2 * Math.PI * radius;
    const offset = circumference - (percentage / 100) * circumference;

    circle.style.strokeDasharray = `${circumference} ${circumference}`;
    circle.style.strokeDashoffset = offset;

    document.getElementById('progressText').textContent = `${percentage}%`;
}

function displayResult(result, type) {
    const executionTime = (endTime - startTime).toFixed(2);
    statusEl.textContent = `Status: Completed (${type})`;
    resultEl.textContent = `Result: Found ${result.length} primes`;
    executionTimeEl.textContent = `Execution Time: ${executionTime} ms`;
    cancelBtn.disabled = true;
}

function terminateWorkers() {
    workerPool.forEach((worker) => worker.terminate());
    workerPool = [];
}
