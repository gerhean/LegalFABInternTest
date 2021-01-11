package com.example.legalfabbacktest;

import java.util.*;

import org.springframework.web.bind.annotation.*;


@RestController
public class LegalFabController {


    private HashMap<Integer, ArrayList<ChildJson>> pidToChildren = new HashMap<>();
    private ParentJson[] parents;

    public LegalFabController() {
        this.parents = ReadJson.readParent();
        Arrays.sort(this.parents);
        HashMap<Integer, ParentJson> pidToParent = new HashMap<>();
        for (ParentJson parent: this.parents) {
            pidToParent.put(parent.id, parent);
            pidToChildren.put(parent.id, new ArrayList<>());
        }
        ChildJson[] children = ReadJson.readChildren();
        for (ChildJson child: children) {
            pidToParent.get(child.parentId).paidAmount += child.paidAmount;
            pidToChildren.get(child.parentId).add(child);
        }
    }

    @CrossOrigin(origins = "http://localhost:8100")
    @GetMapping("/parent")
    public ParentJson[] parentJsons(@RequestParam(value = "page", defaultValue = "0") String pageStr) {
        // http://localhost:8080/parent?page=0
        int page = Integer.parseInt(pageStr) * 2;
        if (page >= parents.length) {
            return new ParentJson[0];
        }
        return Arrays.copyOfRange(parents, page, page + 2);
    }

    @CrossOrigin(origins = "http://localhost:8100")
    @GetMapping("/children")
    public ArrayList<ChildJson> childJsons(@RequestParam(value = "pid", defaultValue = "0") String pidStr) {
        // http://localhost:8080/children?pid=0
        int pid = Integer.parseInt(pidStr);
        return pidToChildren.getOrDefault(pid, new ArrayList<>());
    }

    @CrossOrigin(origins = "http://localhost:8100")
    @GetMapping("/page-count")
    public int pageCount() {
        // http://localhost:8080/page-count
        return (parents.length + 1) / 2;
    }
}
