package linkedlist;

import java.io.File;
import java.io.FilenameFilter;

public class MyFilter implements FilenameFilter
{
    public boolean accept(File dir, String name)
    {
    	  name = name.toUpperCase();
        if (name.endsWith(".JPG") || name.endsWith(".JPEG"))
        {
            return true;
        }
        if (name.endsWith(".PNG"))
        {
            return true;
        }
        if (name.endsWith(".TIF"))
        {
            return true;
        }
        return false;
    }
}
