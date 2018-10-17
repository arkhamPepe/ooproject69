package se.chalmers.group22.gymcompanion.Model;

import lombok.AccessLevel;
import lombok.Getter;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

@Getter
public class Schedule implements Serializable, ISchedule {

    private Reminder reminder;
    private Calendar calendar = new GregorianCalendar();

    @Getter(AccessLevel.NONE)
    private Map<Calendar, Routine> routineSchedule;

    public Schedule(){
        reminder = new Reminder();
        routineSchedule = new HashMap<>();
    }

    /** addRoutine
     * Purpose: Schedule input routine on input date.
     * @param routine
     * @param date
     */
    public void addRoutine(Routine routine, Calendar date){
        routineSchedule.put(date,routine);
    }


    /** removeRoutine
     * Purpose: Remove routine on input date from schedule
     * @param date
     */
    public void removeRoutine(Calendar date){
        routineSchedule.remove(date);
    }

    /** dateHasRoutine
     * @param date
     * @return
     */
    public boolean dateHasRoutine(Calendar date){
        return routineSchedule.containsKey(date);
    }

    /**--------------------------------------------------------------*/
    /**                         GETTERS                              */
    /**--------------------------------------------------------------*/

    /** getRoutineFromDay
     * @param date
     * @return Routine object scheduled on input date
     */
    public Routine getRoutineFromDay(Calendar date){
        for (Calendar calendar : routineSchedule.keySet()){
            if (calendar.get(Calendar.DAY_OF_YEAR) == date.get(Calendar.DAY_OF_YEAR) &&
                    calendar.get(Calendar.YEAR) == date.get(Calendar.YEAR)
            ){
                return routineSchedule.get(calendar);
            }
        }
        return null;
    }

    /** getSchedule
     * @return Defensive copy of the routine schedule.
     */
    public Map<Calendar, Routine> getSchedule() {
        return new HashMap<>(routineSchedule);
    }

    /** getToday
     * @return String representation of the date of today.
     */
    public String getToday(){
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        return date.format(calendar.getTime());
    }

    /** getScheduleKeySet()
     * @return all dates with a scheduled routine.
     */
    public Set<Calendar> getScheduleKeySet(){
        return routineSchedule.keySet();
    }

    /** getLatestFinishedRoutine
     * @return the latest finished routine.
     */
    public Routine getLatestFinishedRoutine() {
        Calendar latestDate = null;
        Routine finishedRoutine = null;
        for (Calendar date : getScheduleKeySet()) {
            if (latestDate == null || date.getTime().after(latestDate.getTime())) {
                latestDate = date;
                finishedRoutine = routineSchedule.get(latestDate);
            }
        }
        return finishedRoutine;
    }

    /** getRoutineNameFromDate
     * @param date
     * @return name of the routine that is scheduled on input date.
     */
    public String getRoutineNameFromDate(Calendar date){
        return getRoutineFromDay(date).getName();
    }

    /** getRoutineNameFromDate
     * @param year
     * @param dayOfYear
     * @return name of the routine that is scheduled on input date.
     */
    public String getRoutineNameFromDate(int year, int dayOfYear){
        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.DAY_OF_YEAR, dayOfYear);

        return getRoutineFromDay(c).getName();
    }

    /** getRoutineNameFromDate
     * @param year
     * @param month
     * @param dayOfMonth
     * @return name of the routine that is scheduled on input date.
     */
    public String getRoutineNameFromDate(int year, int month, int dayOfMonth){
        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        return getRoutineNameFromDate(c);
    }

    public int getYearToday() {
        return calendar.get(Calendar.YEAR);
    }

    public int getMonthToday() {
        return calendar.get(Calendar.MONTH);
    }

    public int getDayOfYearToday(){
        return calendar.get(Calendar.DAY_OF_YEAR);
    }

    public int getDayOfMonthToday() {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
}

