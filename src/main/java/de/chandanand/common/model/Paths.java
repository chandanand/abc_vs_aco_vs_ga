package de.chandanand.common.model;

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

    public int getSize() {
        return paths.size();
    }

    public Path getPathHavingPathNumber(int pathNumber) {
        return paths.get(pathNumber);
    }

    public int getPathNumberHavingNode(int nodeNumber) {
        int pathNumber = 0;
        for (Path path: paths) {
            if (path.contains(nodeNumber))
                break;
            pathNumber++;
        }
        if (pathNumber == 7)
            return -1;

        return pathNumber;
    }

    public void setPathVisited(int pathNumber) {
        paths.get(pathNumber).setVisited(true);
    }

    public List<Path> getPaths() {
        return paths;
    }


    public int fitnessOfPath(Path solved, Path unsolved) {
        int fitness = 0;
        for (Node nodeFromUnsolved: unsolved.getNodes())
            for (Node nodeFromSolved: solved.getNodes())
                if (nodeFromSolved.equals(nodeFromUnsolved))
                    fitness++;
        return fitness;
    }
}
