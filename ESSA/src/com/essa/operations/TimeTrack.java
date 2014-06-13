/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.essa.operations;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Comparator;

/**
 *
 * @author Ekber Selcuk
 */
public class TimeTrack implements Comparable<TimeTrack>, Serializable {

    private static final long serialVersionUID = 1L;
    private long timeTrackID;
    private String employeeID;
    private String employeeName;
    private String client;
    private Calendar start;
    private Calendar finish;
    /*private Date startDate;
     private Date endDate;
     private Time startTime;
     private Time endTime;*/
    private int breakTotal; // in minutes
    private int breakShift;// 1 = between 6:00-14:00, 2 = between 14:00-22:00, 3 = between 22:00-06:00, 4 = equally distributed, 12 = distributed in 1 and 2, 13 = distributed in 1 and 3, 23 = distributed in 2 and 3
    private float employeeDisplacement;
    private String description;
    private float hoursShift1;
    private float hoursShift2;
    private float hoursShift3;
    private float overHoursShift1;
    private float overHoursShift2;
    private float overHoursShift3;

    public TimeTrack(String employeeID, String employeeName, String client, Calendar start, Calendar finish, int breakTotal, int breakShift, float employeeDisplacement, String description) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.client = client;
        this.start = start;
        this.finish = finish;
        /*this.startDate = startDate;
         this.endDate = endDate;
         this.startTime = startTime;
         this.endTime = endTime;*/
        this.breakTotal = breakTotal;
        this.breakShift = breakShift;
        this.employeeDisplacement = employeeDisplacement;
        this.description = description;
    }

    public long getTimeTrackID() {
        return timeTrackID;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Calendar getStart() {
        return start;
    }

    public void setStart(Calendar start) {
        this.start = start;
    }

    public Calendar getFinish() {
        return finish;
    }

    public void setFinish(Calendar finish) {
        this.finish = finish;
    }

    public int getBreakTotal() {
        return breakTotal;
    }

    public void setBreakTotal(int breakTotal) {
        this.breakTotal = breakTotal;
    }

    public float getEmployeeDisplacement() {
        return employeeDisplacement;
    }

    public void setEmployeeDisplacement(float employeeDisplacement) {
        this.employeeDisplacement = employeeDisplacement;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return getEmployeeID() + "\t" + getEmployeeName() + "\t" + getClient()
                + "\t" + getStart() + "\t" + getFinish()
                //+ "\t" + getStartTime().toUniversalString() + "\t" + getEndTime().toUniversalString()
                + "\t" + getBreakTotal() + "\t" + getBreakShift()
                + "\t" + getEmployeeDisplacement() + "\t" + getDescription();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TimeTrack)) {
            return false;
        }
        final TimeTrack t = (TimeTrack) o;
        //System.err.println(this.getEmployeeID()+ this.getClient() + this.getStartDate()+ this.getStartTime().toUniversalString());
        return (this.getEmployeeID() + this.getClient() + this.getStart()).equals(t.getEmployeeID() + t.getClient() + t.getStart());
        //return (this.hashCode() == t.hashCode());
    }

    @Override
    public int hashCode() {
        return (getClient().hashCode() + getEmployeeID().hashCode() + getStart().hashCode());
    }

    @Override
    public int compareTo(TimeTrack t) {
        if (t == null) {
            throw new NullPointerException();
        } else if (this.equals(t)) {
            return 0;
        } else {
            return 1;
            //return this.compareTo(t);
        }
    }

    // inner class for ordering based on SSnumber
    public static Comparator<TimeTrack> getClientComparator() {
        return new Comparator<TimeTrack>() {
            @Override
            public int compare(TimeTrack t1, TimeTrack t2) {
                if (!(t1.equals(t2))) {
                    return t1.getClient().compareTo(t2.getClient());
                } else {
                    return 0;
                }
            }
        };
    }

    public static Comparator<TimeTrack> getStartDateComparator() {
        return new Comparator<TimeTrack>() {
            @Override
            public int compare(TimeTrack t1, TimeTrack t2) {
                if (!(t1.equals(t2))) {
                    return t1.getStart().compareTo(t2.getStart());
                } else {
                    return 0;
                }
            }
        };
    }

    public float getHoursShift1() {
        return hoursShift1;
    }

    public void setHoursShift1(float hoursShift1) {
        this.hoursShift1 = hoursShift1;
    }

    public float getHoursShift2() {
        return hoursShift2;
    }

    public void setHoursShift2(float hoursShift2) {
        this.hoursShift2 = hoursShift2;
    }

    public float getHoursShift3() {
        return hoursShift3;
    }

    public void setHoursShift3(float hoursShift3) {
        this.hoursShift3 = hoursShift3;
    }

    public float getOverHoursShift1() {
        return overHoursShift1;
    }

    public void setOverHoursShift1(float overHoursShift1) {
        this.overHoursShift1 = overHoursShift1;
    }

    public float getOverHoursShift2() {
        return overHoursShift2;
    }

    public void setOverHoursShift2(float overHoursShift2) {
        this.overHoursShift2 = overHoursShift2;
    }

    public float getOverHoursShift3() {
        return overHoursShift3;
    }

    public void setOverHoursShift3(float overHoursShift3) {
        this.overHoursShift3 = overHoursShift3;
    }

    public int getBreakShift() {
        return breakShift;
    }

    public void setBreakShift(int breakShift) {
        this.breakShift = breakShift;
    }
}
