package com.example.intellifytask;

import java.util.ArrayList;
import java.util.List;

public class StudentObjectClass {

    public String message;
    public List<Attendance> attendance;

    StudentObjectClass() {
        attendance = new ArrayList<>();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Attendance> getAttendance() {
        return attendance;
    }

    public void setAttendance(String cname, int total, int present, int absent, int sick, int late) {
        attendance.add(new Attendance(cname, total, present, absent, sick, late));
    }

    public class Attendance {

        public String className;
        public int totalLectures;
        public int present;
        public int sick;
        public int absent;
        public int late;
        Attendance(String className, int totalLectures, int present, int absent, int sick, int late) {
            this.className = className;
            this.present = present;
            this.totalLectures = totalLectures;
            this.absent = absent;
            this.sick = sick;
            this.late = late;
        }
        Attendance() {
        }

        public int getAbsent() {
            return absent;
        }

        public void setAbsent(int absent) {
            this.absent = absent;
        }

        public int getPresent() {
            return present;
        }

        public void setPresent(int present) {
            this.present = present;
        }

        public int getSick() {
            return sick;
        }

        public void setSick(int sick) {
            this.sick = sick;
        }

        public int getLate() {
            return late;
        }

        public void setLate(int late) {
            this.late = late;
        }

        public int getTotalLectures() {
            return totalLectures;
        }

        public void setTotalLectures(int totalLectures) {
            this.totalLectures = totalLectures;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }
    }
}