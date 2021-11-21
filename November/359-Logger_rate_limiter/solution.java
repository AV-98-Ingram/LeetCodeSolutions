class Logger {

    Map<String, Integer> map = new HashMap<>();
    
    public Logger() {
        
    }
    
    public boolean shouldPrintMessage(int timestamp, String message) {
	Integer oldTime = map.get(message);

	if (oldTime == null || oldTime + 10 <= timestamp) {
	    map.put(message, timestamp);
	    return true;
	}
	return false;
    }
}

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */
