package leetcode1;

import util.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

// 警告一小时内使用相同员工卡大于等于三次的人
// https://leetcode-cn.com/problems/alert-using-same-key-card-three-or-more-times-in-a-one-hour-period/
public class AlertUseKeyCard {

    public List<String> alertNames(String[] keyName, String[] keyTime) {
        List<String> ret = new ArrayList<>();
        Record[] records = new Record[keyName.length];
        for (int i = 0; i < keyName.length; i++) {
            records[i] = new Record(keyName[i], keyTime[i]);
        }
        // 排序后name是挨着的，时间是从小到大
        Arrays.sort(records, new RecordComparator());

        String lastName = records[0].name;
        int lastIndex = 0;

        for (int i = 1; i < records.length; i++) {
            String currentName = records[i].name;
            boolean isLastOne = (i == records.length - 1);
            if (isLastOne || !currentName.equals(lastName)) {
                if (inOneHour(Arrays.copyOfRange(records, lastIndex, isLastOne ? records.length : i))) {
                    ret.add(lastName);
                }
                lastName = currentName;
                lastIndex = i;
            }
        }
//        Collections.sort(ret);
        return ret;
    }

    /**
     * @param records 某人的时间列表，无序
     * @return 是不是存在一个小时3次
     */
    private boolean inOneHour(Record[] records) {
        if (records.length > 2) {
            Time[] times = new Time[records.length];
            for (int i = 0; i < records.length; i++) {
                times[i] = records[i].time;
            }

            for (int i = 0; i < times.length -2; i++) {
                Time t1 = times[i];
                Time t2 = times[i + 1];
                Time t3 = times[i + 2];
                if (t1.inOneHour(t2) && t1.inOneHour(t3)) {
                    return true;
                }
            }
        }
        return false;
    }

    static class Record {
        String name;
        Time time;
        public Record(String name, String time) {
            this.name = name;
            this.time = new Time(time);
        }
    }

    static class RecordComparator implements Comparator<Record> {
        @Override
        public int compare(Record o1, Record o2) {
            if (o1.name.equals(o2.name)) {
                if (o1.time.hour == o2.time.hour) {
                    return o1.time.min - o2.time.min;
                }
                return o1.time.hour - o2.time.hour;
            } else {
                return o1.name.compareTo(o2.name);
            }
        }
    }

    static class Time {
        int hour;
        int min;
        public Time(String s) {
            hour = Integer.parseInt(s.substring(0, s.indexOf(':')));
            min = Integer.parseInt(s.substring(s.indexOf(':') + 1, s.length()));
        }

        // t应该晚于当前对象
        public boolean inOneHour(Time t) {
            return t.hour == hour || (t.hour - hour == 1 && t.min <= min);
        }

        @Override
        public String toString() {
            return "Time{" +
                    "hour=" + hour +
                    ", min=" + min +
                    '}';
        }
    }

    public static void main(String[] args) {

    }

}
