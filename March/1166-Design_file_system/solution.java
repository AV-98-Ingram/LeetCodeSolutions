class FileSystem {

    class FS {
	Map<String, FS> child = new HashMap<>();
	int val;

	FS get(String s) {
	    return child.get(s);
	}

	void put(String s, FS fs) {
	    child.put(s, fs);
	}
    }

    FS root = new FS();
    
    public FileSystem() {

    }
    
    public boolean createPath(String path, int value) {
	String[] pathStrs = path.split("/");
	int len = pathStrs.length;
	FS fs = root;
	
	for (int i = 1; i < len-1; i++) {
	    fs = fs.get(pathStrs[i]);
	    if (fs == null)
		return false;
	}
	
	FS dest = fs.get(pathStrs[len-1]);
	
	if (dest != null)
	    return false;
	dest = new FS();
	dest.val = value;
	fs.put(pathStrs[len-1], dest);
	return true;
    }
    
    public int get(String path) {
        FS fs = root;
	String[] pathStrs = path.split("/");
	int len = pathStrs.length;
	
	for (int i = 1; i < len; i++) {
	    fs = fs.get(pathStrs[i]);
	    if (fs == null)
		return -1;
	}
	return fs.val;
    }


}
