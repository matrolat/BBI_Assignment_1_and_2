onmessage = function (e) {
    const { start, end } = e.data;
    const primes = [];
    
    for (let i = start; i <= end; i++) {
      if (isPrime(i)) primes.push(i);
    }
    
    postMessage(primes);
  };
  
  function isPrime(num) {
    for (let i = 2, sqrt = Math.sqrt(num); i <= sqrt; i++) {
      if (num % i === 0) return false;
    }
    return num > 1;
  }
  