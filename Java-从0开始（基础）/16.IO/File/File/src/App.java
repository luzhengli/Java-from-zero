import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.nio.file.Paths;
import java.nio.file.Path;

import static java.lang.System.out;

public class App {
    public static void main(String[] args) throws Exception {
        // 1. File 对象
        // String path = "D:\\Anaconda";
        // File f = new File(path);
        // out.println(f.getAbsolutePath());
        // File[] fList = f.listFiles(); // 列出该目录下所有文件和子目录
        // printFiles(fList);

        // File[] fList2 = f.listFiles(new FilenameFilter() {
        // public boolean accept(File dir, String name) {
        // return name.endsWith(".exe");
        // }
        // });

        // printFiles(fList2);

        // 2. Path 对象
        // Path p = Paths.get("D:", "Anaconda");
        // out.println(p); // D:\Anaconda\bin
        // for (Path path : Paths.get("..").toAbsolutePath()) {
        //     out.println("   " + path);
        // }

        // 3. 递归打印
        // File f = new File("D:\\Anaconda\\etc");
        // listDir(f.getCanonicalFile(), "");

    }

    static void printFiles(File[] files) {
        out.println("===start===");
        if (files != null) {
            for (File file : files) {
                out.println(file);
            }
        }
        out.println("===end===");
    }

    static void listDir(File dir, String t) {
        // TODO: 递归打印所有文件和子文件夹的内容
        File[] fs = dir.listFiles();
        if (fs != null) {
            for (File f : fs) {
                System.out.println(t + f.getName() + "/");
                if (f.isDirectory()) {
                    listDir(f, t + "  ");
                }
            }
        }
    }
}
