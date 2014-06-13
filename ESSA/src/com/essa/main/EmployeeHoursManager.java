/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.essa.main;

import com.essa.operations.TimeTrack;
import com.essa.util.ESSATime;
import com.essa.util.FileAndObjectOIStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class EmployeeHoursManager {

    //private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
    private static FileAndObjectOIStream fileAndObjectOIStream;
    private static String fileName;
    private static Calendar sMorningShift = null, sNoonShift = null, sNightShift = null, midNight = null;
    private static ESSATime hoursInShift1 = null, hoursInShift2 = null, hoursInShift3 = null;
    //private static Time sMorningShift = null, sNoonShift = null, sNightShift = null, midNight = null;

    public static void main(String[] args) throws Exception {
        try {
            SortedSet<TimeTrack> timeTracks = new TreeSet<>();
            fileName = "timetrack.ser";
            addTimeTrack(timeTracks);
            calculateNetHours(timeTracks);
            // listTimeTrack();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void addTimeTrack(Set<TimeTrack> timeTracks) throws ParseException {

        try {
            System.err.println("test");

            timeTracks.add(new TimeTrack("74041446122", "Selcuk", "Ijsboerke1", new GregorianCalendar(2014, 11, 30, 6, 00, 00), new GregorianCalendar(2014, 11, 30, 14, 30, 00), 30, 1, 100F, "OK"));

            /*timeTracks.add(new TimeTrack("74041446122", "Selcuk", "Glacio", new GregorianCalendar(2014, 4, 23, 20, 30, 00), new GregorianCalendar(2014, 4, 24, 7, 30, 00), 30, 1, 100F, "OK"));
             timeTracks.add(new TimeTrack("74041446121", "Selcuk", "Glacio", new GregorianCalendar(2014, 4, 24, 22, 30, 00), new GregorianCalendar(2014, 4, 24, 22, 30, 00), 30, 2, 100F, "OK"));
             timeTracks.add(new TimeTrack("74041446122", "Selcuk", "Glacio", new GregorianCalendar(2014, 4, 23, 23, 30, 00), new GregorianCalendar(2014, 4, 24, 22, 30, 00), 30, 3, 100F, "OK"));
             timeTracks.add(new TimeTrack("74041446122", "Selcuk", "Glacio", new GregorianCalendar(2014, 4, 23, 22, 30, 00), new GregorianCalendar(2014, 4, 24, 22, 30, 00), 30, 2, 100F, "OK"));
             */
            System.err.println("test 1");
            fileAndObjectOIStream.fileObjectOutput(timeTracks, fileName);
            System.err.println("test 2");

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    private static void listTimeTrack() {
        System.out.println("test 3");
        System.out.print("Employee id \tName \tClient \tStart date \tEnd date \t");
        System.out.println("Start time \tEnd time \tBreak period \tDisp \tDescription");

        fileAndObjectOIStream.fileObjectInput(fileName);

        System.out.println("test 4");
    }

    private static void calculateNetHours(Set<TimeTrack> set) throws Exception {
        TimeTrack[] timeTracks = new TimeTrack[set.size()];

        int i = 0;
        for (TimeTrack timeTrack : set) {
            Calendar start = timeTrack.getStart();
            Calendar finish = timeTrack.getFinish();

            System.out.println("start: " + new SimpleDateFormat("yyyy MMM dd HH:mm:ss").format(start.getTime()));
            System.out.println("finish: " + new SimpleDateFormat("yyyy MMM dd HH:mm:ss").format(finish.getTime()));

            if (start.compareTo(finish) > 0) {
                System.err.println("finish date-time cannot be before start date-time!");
            }

            int shift = 0, day = 0, noon = 0, night = 0,
                    dayOverHours = 0, noonOverHours = 0, nightOverHours = 0;
            //int[] shifts = new int[]{0,0,0};

            int numOfDays = (int) ((finish.getTimeInMillis() - start.getTimeInMillis()) / (1000 * 60 * 60 * 24));
            System.out.println("numOfDays:" + numOfDays);

            //subtract break
            finish.add(Calendar.MINUTE, -timeTrack.getBreakTotal());
            
            for (i = 0; start.compareTo(finish) < 0; i++) {
                shift = findShift(start);
                if (shift == 1) {
                    if (day + noon + night < 7.5 * 60) {
                        day += 1;
                    } else {
                        dayOverHours += 1;
                    }

                } else if (shift == 2) {
                    if (day + noon + night < 7.5 * 60) {
                        noon += 1;
                    } else {
                        noonOverHours += 1;
                    }

                } else if (shift == 3) {
                    if (day + noon + night < 7.5 * 60) {
                        night += 1;
                    } else {
                        nightOverHours += 1;
                    }

                } else {
                    System.out.println("something is wrong.");
                }
                start.add(Calendar.MINUTE, 1);
                //System.out.println(startAsMinutes == aDayAsMinutes);
            }
            
            timeTrack.setHoursShift1(day / 60F);
            timeTrack.setHoursShift2(noon / 60F);
            timeTrack.setHoursShift3(night / 60F);
            timeTrack.setOverHoursShift1(dayOverHours / 60F);
            timeTrack.setOverHoursShift2(noonOverHours / 60F);
            timeTrack.setOverHoursShift3(nightOverHours / 60F);
            day = noon = night = dayOverHours = noonOverHours = nightOverHours = 0;

            System.out.println("day: " + timeTrack.getHoursShift1());
            System.out.println("noon: " + timeTrack.getHoursShift2());
            System.out.println("night: " + timeTrack.getHoursShift3());
            System.out.println("dayOverHours: " + timeTrack.getOverHoursShift1());
            System.out.println("noonOverHours: " + timeTrack.getOverHoursShift2());
            System.out.println("nightOverHours: " + timeTrack.getOverHoursShift3());

        }
    }

    private static int findShift(Calendar calendar) {

        sMorningShift = (Calendar) calendar.clone();
        sMorningShift.set(Calendar.HOUR_OF_DAY, 6);
        sMorningShift.set(Calendar.MINUTE, 0);
        sMorningShift.set(Calendar.SECOND, 0);
        //System.out.println("sMorningShift: " + new SimpleDateFormat("yyyy MMM dd HH:mm:ss").format(sMorningShift.getTime()));

        sNoonShift = (Calendar) calendar.clone();
        sNoonShift.set(Calendar.HOUR_OF_DAY, 14);
        sNoonShift.set(Calendar.MINUTE, 0);
        sNoonShift.set(Calendar.SECOND, 0);
        //System.out.println("sNoonShift: " + new SimpleDateFormat("yyyy MMM dd HH:mm:ss").format(sNoonShift.getTime()));

        sNightShift = (Calendar) calendar.clone();
        sNightShift.set(Calendar.HOUR_OF_DAY, 22);
        sNightShift.set(Calendar.MINUTE, 0);
        sNightShift.set(Calendar.SECOND, 0);
        //System.out.println("sNightShift: " + new SimpleDateFormat("yyyy MMM dd HH:mm:ss").format(sNightShift.getTime()));

        midNight = (Calendar) calendar.clone();
        midNight.set(Calendar.HOUR_OF_DAY, 24);
        midNight.set(Calendar.MINUTE, 0);
        midNight.set(Calendar.SECOND, 0);

        //System.out.println("midNight: " + new SimpleDateFormat("yyyy MMM dd HH:mm:ss").format(midNight.getTime()));
        //SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        //System.out.print(sdf.format(calendar.getTime())+"?"+sdf.format(sNightShift.getTime()) + " && "+sdf.format(calendar.getTime())+"?"+sdf.format(sMorningShift.getTime()));
        long start = calendar.get(Calendar.HOUR_OF_DAY) * 60;
        //System.out.println("start: " + start);
        long morning = sMorningShift.get(Calendar.HOUR_OF_DAY) * 60;
        //System.out.println("morning: " + morning);
        long noon = sNoonShift.get(Calendar.HOUR_OF_DAY) * 60;
        // System.out.println("noon: " + noon);
        long night = sNightShift.get(Calendar.HOUR_OF_DAY) * 60;
        //System.out.println("night: " + night);
        long midnight = midNight.get(Calendar.HOUR_OF_DAY) * 60;
        //System.out.println("midnight: " + midnight);

        if (start >= morning && start < noon) {
            return 1;
        } else if (start >= noon && start < night) {
            return 2;
        } else if (start >= night || start < morning) {
            return 3;
        }
        return 0;
    }
}
