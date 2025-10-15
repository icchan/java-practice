package org.ian;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

/**
 * This is the solution
 */
public class Solution {
    private static final Logger log = LoggerFactory.getLogger(Solution.class);

    /**
     * Runs the solution
     */
    public void run() {
        log.info("ran solution");

        List<String> URLS = List.of("/hello", "/user/{1}/profile", "/home/settings/security", "/user/privacy", "/user/{1}");

        Router router = new Router();

        for (String url : URLS) {
            log.info(url);
            router.addRoute(url, p -> "I'm a router for " + url + " handling " + p);
        }

        router.followRoute("/user/1");

    }

}

// classes TODO move to their own file
@FunctionalInterface
interface RouteHandler extends Function<String, String> {
}

class Router {
    private PathNode root = new PathNode(); // prefix tree

    public void addRoute(String url, RouteHandler handler) {
        PathNode current = root; // start at the top

        String[] segments = url.split("/");
        for (String segment : segments) {
            if (segment != null && !segment.isEmpty()) {
                if (segment.startsWith("{") && segment.endsWith("}")){
                    // its a wildcard placeholder
                    segment = "*";
                }

                current = current.getChild(segment); // this will create if it doesnt exist
            }
        }
        // finally add the handler
        current.setHandler(handler);
        root.print("");
    }

    public void followRoute(String url){
        PathNode current = root; // start at the top

        String[] segments = url.split("/");
        for (String segment : segments) {
            if (current.hasChild(segment)) {
                current = current.getChild(segment); // if its there follow the tree
            } else if (current.hasChild("*")){
                current = current.getChild("*"); // else check for wildcard child
            } else {
                // no current route
                // TODO what if route is not found?
            }
        }

        System.out.println(current.getHandler().apply(url));

    }
}

class PathNode {
    private Map<String, PathNode> children = new HashMap<>();
    private RouteHandler handler;

    public PathNode getChild(String path) {
        return children.computeIfAbsent(path, c -> new PathNode());
    }
    public boolean hasChild(String url) {
        return children.containsKey(url);
    }

    public void setHandler(RouteHandler input) {
        this.handler = input;
    }

    public RouteHandler getHandler() {
        return this.handler;
    }

    public void print(String prefix) {
        for (Map.Entry<String, PathNode> child : children.entrySet()) {
            System.out.println(prefix + "/" + child.getKey() + (child.getValue().handler != null ? " @" : " ."));
            child.getValue().print(prefix + "  ");
        }
    }

}
