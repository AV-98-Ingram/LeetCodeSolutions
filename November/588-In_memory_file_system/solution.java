import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

class FileSystem {

    interface AbstractDocs {
	String name();
    }

    class Dir implements AbstractDocs {
	TreeMap<String, AbstractDocs> docs;
	String name;

	Dir(String name) {
	    this.docs = new TreeMap<>();
	    this.name = name;
	}

	Dir() { // create root dir
	    this.docs = new TreeMap<>();
	    this.name = "";
	}

	@Override
	public String name() {
	    return name;
	}

	public String toString() {
	    return name;
	}
    }

    class File implements AbstractDocs {
	List<String> contents;
	String name;

	File(String name) {
	    contents = new LinkedList<>();
	    this.name = name;
	}

	@Override
	public String name() {
	    return name;
	}

	public String toString() {
	    return name;
	}
    }

    Dir root;

    public FileSystem() {
	this.root = new Dir();
    }

    public List<String> ls(String path) {
	AbstractDocs doc = root;
	List<String> result = new LinkedList<>();

	for (String subDir : parsePath(path))
	    // the cast should be fine and will be no null-deref as
	    // the input must be valid
	    doc = ((Dir) doc).docs.get(subDir);
	if (doc instanceof File)
	    result.add(doc.name());
	else
	    for (AbstractDocs ad : ((Dir) doc).docs.values()) {
		result.add(ad.name());
	    }
	return result;
    }

    public void mkdir(String path) {
	AbstractDocs doc = root;

	for (String subDirName : parsePath(path)) {
	    Dir dir = (Dir) doc;
	    AbstractDocs subDir = dir.docs.get(subDirName);

	    if (subDir == null) {
		subDir = new Dir(subDirName);
		dir.docs.put(subDirName, subDir);
	    }
	    doc = subDir;
	}
    }

    public void addContentToFile(String filePath, String content) {
	AbstractDocs doc = root;
	AbstractDocs parent = root;
	String[] subDirNames = parsePath(filePath);

	for (String subDirName : subDirNames) {
	    parent = doc;
	    doc = ((Dir) doc).docs.get(subDirName);
	}
	if (doc == null) {
	    doc = new File(subDirNames[subDirNames.length - 1]);
	    ((Dir) parent).docs.put(doc.name(), doc);
	}
	((File) doc).contents.add(content);
    }

    public String readContentFromFile(String filePath) {
	AbstractDocs doc = root;
	String[] subDirNames = parsePath(filePath);

	for (String subDirName : subDirNames)
	    doc = ((Dir) doc).docs.get(subDirName);

	String result = "";

	for (String content : ((File) doc).contents)
	    result += content;
	return result;
    }

    private String[] parsePath(String path) {
	String[] ret = path.split("/");

	if (ret.length > 0 && ret[0].length() == 0)
	    return Arrays.copyOfRange(ret, 1, ret.length);
	return ret;
    }

    public static void main(String[] args) {
	FileSystem fs = new FileSystem();

	System.out.print(fs.ls("/"));
	fs.mkdir("/a/b/c");
	fs.addContentToFile("/a/b/c/d", "hello");
	System.out.print(fs.ls("/"));
	System.out.print(fs.readContentFromFile("a/b/c/d"));
    }
}
