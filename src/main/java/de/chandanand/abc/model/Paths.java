package de.chandanand.abc.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chand on 7/8/16.
 */
public class Paths {

    private List<Path> paths = new ArrayList<>();


    public boolean contains(Path newPath) {
        return paths.contains(newPath);
    }

    public void addPath(Path newPath) {
        paths.add(newPath);
    }
}
