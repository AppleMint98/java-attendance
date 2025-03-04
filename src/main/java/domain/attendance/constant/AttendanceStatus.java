package domain.attendance.constant;

import domain.attendance.AttendanceRule;
import domain.attendance.MondayAttendanceRule;
import domain.attendance.WeekdayAttendanceRule;
import domain.datetime.CampusDate;
import domain.datetime.CampusTime;
import java.time.DayOfWeek;

public enum AttendanceStatus {
    ATTENDANCE("출석"),
    TARDINESS("지각"),
    ABSENCE("결석");

    private final String value;

    private static final AttendanceRule mondayRule = new MondayAttendanceRule();
    private static final AttendanceRule weekdayRule = new WeekdayAttendanceRule();

    AttendanceStatus(String value) {
        this.value = value;
    }

    public static AttendanceStatus calculateByDateAndTime(final CampusDate date, final CampusTime time) {
        return getAttendanceRule(date).calculateStatus(time);
    }

    private static AttendanceRule getAttendanceRule(final CampusDate date) {
        if (date.getDayOfWeek() == DayOfWeek.MONDAY) {
            return mondayRule;
        }
        return weekdayRule;
    }

    public String getValue() {
        return value;
    }
}
