package cn.edu.seu.alumni.util;

import android.content.res.AssetManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.lang.Throwable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


import cn.edu.seu.alumni.application.App;

public class DataProvider {

    public static class SeuMajors{
        private List<String> departments;
        private Map<String, List<String>> majors;
        public SeuMajors() {
        }
        public SeuMajors(List<String> departments, Map<String, List<String>> majors) {
            this.departments = departments;
            this.majors = majors;
        }
        public List<String> getDepartments() {
            return departments;
        }
        public Map<String, List<String>> getMajors() {
            return majors;
        }
    }

    public static SeuMajors getSeuMajorsData(){

        List<String> departments = new ArrayList<>();
        Map<String, List<String>> majors = new HashMap<>();

        StringBuilder buffer = new StringBuilder();
        AssetManager asset = App.getContext().getAssets();
        Scanner scanner = null;
        try{
            scanner = new Scanner(asset.open("seu_majors.json"));
            while (scanner.hasNextLine()) {
                buffer.append(scanner.nextLine());
            }
        }catch (Exception e) {
            return null;
        } finally {
            if (null != scanner) {
                scanner.close();
            }
        }
        try{
            JSONArray array = new JSONArray(buffer.toString());
            JSONArray major = null;
            JSONObject obj = null;
            String departmentName = null;
            List<String> majorList = new ArrayList<>();
            for(int i = 0; i < array.length(); i++){
                obj = array.getJSONObject(i);
                major = obj.getJSONArray("majors");
                departmentName = obj.getString("department_name");
                departments.add(departmentName);
                for(int j = 0; j < major.length(); j++){
                    majorList.add(major.getString(j));
                }
                Collections.sort(majorList);
                majors.put(departmentName, majorList);
            }
            Collections.sort(departments);
            return new SeuMajors(departments, majors);
        }catch(Exception e){
            return null;
        }
    }
}
