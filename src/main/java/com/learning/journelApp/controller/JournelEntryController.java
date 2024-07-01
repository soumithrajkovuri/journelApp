package com.learning.journelApp.controller;

import com.learning.journelApp.entity.JournelCopy;
import com.learning.journelApp.entity.JournelEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("test")
public class JournelEntryController {
    private Map<Long, JournelCopy> map= new HashMap<>();

    @GetMapping
    public List<JournelCopy> getAll(){
        return new ArrayList<JournelCopy>(map.values());
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournelCopy myEntry){
        map.put(myEntry.getId(), myEntry);
        return true;
    }

    @GetMapping("id/{myId}")
    public JournelCopy findById(@PathVariable Long myId){
        return map.get(myId);
    }
}
