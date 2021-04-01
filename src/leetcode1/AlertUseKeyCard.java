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
        Arrays.sort(records, new RecordComparator());

        String lastName = records[0].name;
        int lastIndex = 0;


        for (int i = 1; i < records.length; i++) {
            String currentName = records[i].name;
            if (i == records.length - 1 || !currentName.equals(lastName)) {
                if (inOneHour(Arrays.copyOfRange(records, lastIndex, (i == records.length - 1) ? records.length : i))) {
                    ret.add(lastName);
                }
                lastName = currentName;
                lastIndex = i;
            }
        }

        Collections.sort(ret);

        return ret;
    }



    /**
     * @param records 某人的时间列表，无序
     * @return 是不是存在一个小时3次
     */
    private boolean inOneHour(Record[] records) {
        Time[] t = new Time[records.length];
        for (int i = 0; i < records.length; i++) {
            t[i] = records[i].time;
        }
        Arrays.sort(t, new Comparator<Time>() {
            @Override
            public int compare(Time o1, Time o2) {
                if (o1.hour == o2.hour) {
                    return o1.min - o2.min;
                }
                return o1.hour - o2.hour;
            }
        });
        if (t.length > 2) {
            for (int i = 0; i < t.length -2; i++) {
                Time t1 = t[i];
                Time t2 = t[i + 1];
                Time t3 = t[i + 2];
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
            return o1.name.compareTo(o2.name);
        }
    }

    static class Time {
        int hour;
        int min;
        public Time(String s) {
            hour = Integer.parseInt(s.substring(0, s.indexOf(':')));
            min = Integer.parseInt(s.substring(s.indexOf(':') + 1, s.length()));
        }

        @Override
        public String toString() {
            return "Time{" +
                    "hour=" + hour +
                    ", min=" + min +
                    '}';
        }

        // t应该晚于当前对象
        public boolean inOneHour(Time t) {
            return t.hour == hour || (t.hour - hour == 1 && t.min <= min);
        }
    }

    public static void main(String[] args) {

//        String[] s1 = new String[]{"a","a","a","a","a","b","b","b","b","b","b"};
//        String[] s2 = new String[]{"23:20","11:09","23:30","23:02","15:28","22:57","23:40","03:43","21:55","20:38","00:19"};
        String[] s1 = new String[]{"leslie","leslie","leslie","clare","clare","clare","clare"};
        String[] s2 = new String[]{"13:00","13:20","14:00","18:00","18:51","19:30","19:49"};
        Utils.print(new AlertUseKeyCard().alertNames(s1, s2));
//        Utils.print("" + new AlertUseKeyCard().inOneHour(new String[]{"21:00","21:20","21:30","23:00"}));
//        Utils.print("" + new Time("10:00").inOneHour(new Time("11:00")));

//        Time[] t = new Time[]{new Time("1:22"), new Time("5:08"), new Time("1:40"), new Time("4:08") };
//        Arrays.sort(t, new Comparator<Time>() {
//            @Override
//            public int compare(Time o1, Time o2) {
//                if (o1.hour == o2.hour) {
//                    return o1.min - o2.min;
//                }
//                return o1.hour - o2.hour;
//            }
//        });
//        for (Time t0 : t) {
//            Utils.print(t0 + "");
//        }
    }

}
