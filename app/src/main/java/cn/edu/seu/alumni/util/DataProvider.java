package cn.edu.seu.alumni.util;

import android.content.res.AssetManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.lang.Throwable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


import cn.edu.seu.alumni.application.App;

public class DataProvider {

    public static class SeuMajors{
        /**
         * 此处使用ArrayList而不使用List接口是为了OptionsPickerView的使用
         */
        private ArrayList<String> departments;
        private ArrayList<ArrayList<String>> majors;
        public SeuMajors(ArrayList<String> departments, ArrayList<ArrayList<String>> majors) {
            this.departments = departments;
            this.majors = majors;
        }
        public ArrayList<String> getDepartments() {
            return departments;
        }
        public ArrayList<ArrayList<String>> getMajors() {
            return majors;
        }
    }

    public static SeuMajors getSeuMajorsData(){

        ArrayList<String> departments = new ArrayList<>();
        ArrayList<ArrayList<String>> majors = new ArrayList<>();
        Map<String, ArrayList<String>> majorMap = new HashMap<>();

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

            Comparator chineseSort = Collator.getInstance(java.util.Locale.CHINA);
            for(int i = 0; i < array.length(); i++){
                obj = array.getJSONObject(i);
                major = obj.getJSONArray("majors");
                departmentName = obj.getString("department_name");
                departments.add(departmentName);
                ArrayList<String> majorList = new ArrayList<>();
                for(int j = 0; j < major.length(); j++){
                    majorList.add(major.getString(j));
                }
                //中文按拼音序排序
                Collections.sort(majorList, chineseSort);
                majorMap.put(departmentName, majorList);
            }
            Collections.sort(departments, chineseSort);
            for(int i = 0; i < departments.size(); i++){
                for(int j = 0; j < majorMap.get(departments.get(i)).size(); j++){
                    System.out.println(majorMap.get(departments.get(i)).get(j));
                }


                majors.add(majorMap.get(departments.get(i)));
            }
            return new SeuMajors(departments, majors);
        }catch(Exception e){
            return null;
        }
    }
}
