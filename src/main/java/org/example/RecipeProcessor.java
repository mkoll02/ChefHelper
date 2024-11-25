package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RecipeProcessor {

    public List<Integer> indexes(String key, String recipe) {    //find occurrences of symbol in recipe
        return Pattern.compile(Pattern.quote(key)).matcher(recipe).results().map(MatchResult::start).collect(Collectors.toList());
    }
    public String checkIfExists(String r, int i1, int i2) {
        if(i2 != -1) //check that there is something close
            r = r.substring(i1, i2);
        return r;
    }
    public String checkIfExists(String r, int i1, int i2, int i3) {
        if(i2 != -1) return r.substring(i1, i2);
        return checkIfExists(r, i1, i3);
    }

//    public int closest(String toCheck, String s1, String s2, String s3) {
//        List<Integer> l = new ArrayList<>();
//        l.add(toCheck.indexOf(s1));
//        l.add(toCheck.indexOf(s2));
//        l.add(toCheck.indexOf(s3));
//        //List<Integer> validIndices = l.stream().filter(index -> index != -1).toList();
//
//        // If no valid indices exist, return -1
//       // if (validIndices.isEmpty()) return -1;
//
//        return Collections.min(l);
//    }

    public List<String> isolateString(String recipe, List<Integer> indexes, String s1, String s2) {
        List<String> u = new ArrayList<>();
        String r = recipe;

        for(int i = 0; i < indexes.size(); i++) {
            if(i != (indexes.size()-1 ))  //to check if it's the last
                r = r.substring(indexes.get(i), indexes.get(i + 1));  //keep until the next to limit substring

            //r = checkIfExists(r, indexes.get(i), closest(r, s1, s2, "."));
            r = checkIfExists(r, indexes.get(i)+1, r.indexOf("}")+1, r.indexOf(" "));
            u.add(r);
        }
        return u;
    }

}
