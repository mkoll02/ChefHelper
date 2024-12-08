package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RecipeProcessor {

    public List<Integer> indexes(String key, String recipe) {    //find occurrences of symbol in recipe
        return Pattern.compile(Pattern.quote(key)).matcher(recipe).results().map(MatchResult::start).collect(Collectors.toList());
    }

    public String stringIfExists(String r, int start, int end) {
        if(end != -1) //check that there is something close
            return r.substring(start, end); //create substring
        return r;
    }

    public int indexToClosest(int i1, int i2) {//returns the closest symbol index or -1 if there's nothing
        if(i1 != -1 && (i2 == -1 || i1<i2)) return i1;
        return i2;
    }

    public List<String> isolateString(String str, List<Integer> indexes, String s1, String s2) {
        String r;
        List<String> u = new ArrayList<>();
        int start, end, closestSymbol;

        for (int i = 0; i < indexes.size(); i++) {
            r = str;
            start = indexes.get(i);
            end = (i < indexes.size() - 1) ? indexes.get(i + 1) : str.length();

            if (start >= r.length()) continue; // skip if start is out of string

            end = Math.min(end, r.length()); //end to be within bounds
            r = r.substring(start, end);

            closestSymbol = indexToClosest(r.indexOf(s1), r.indexOf(s2)); //find the closest symbol index
            r = stringIfExists(r, 0, closestSymbol); //substring if exists same string if not

            if(r.equals(stringIfExists(r, 1, r.indexOf("}")))) r = stringIfExists(r, 1, r.indexOf(" "));
            else r = stringIfExists(r, 1, r.indexOf("}")+1);

            u.add(r.trim()); // add to list
        }
        return u;
    }

    public String extractName(String str) {//string before {
        if(str.isEmpty()) return "";
        return stringIfExists(str, 0, str.indexOf("{"));
    }

    public String insideBrackets(String str) {
        if(str.equals("{}")) return "";
        return stringIfExists(str, 1, str.indexOf("}"));
    }

    public double extractNumberOf(String insideBrackets) { //the number before %
        if(insideBrackets.isEmpty()) return 1;
        String s = stringIfExists(insideBrackets, 0, insideBrackets.indexOf("%"));
        return Double.parseDouble(s);
    }

    public String extractMeasurement(String insideBrackets) {//from measurement to end
        return insideBrackets.substring(insideBrackets.indexOf("%")+1);
    }
}
