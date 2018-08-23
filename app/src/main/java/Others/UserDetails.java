package Others;

public class UserDetails {
    private static String name;
    private static String empCode;
    private static String date;
    private static String gid;
    private static String empName;
    private static String entyGid;

    public static void setName(String name) {
        UserDetails.name = name;
    }



    public static void setEmpCode(String empCode) {
        UserDetails.empCode = empCode;
    }

    public static void setDate(String date) {
        UserDetails.date = date;
    }

    public static void setEmpGid(String gid) {
        UserDetails.gid = gid;
    }

    public static void setEmpName(String empName) {
        UserDetails.empName = empName;
    }

    public static void setEntyGid(String entyGid) {
        UserDetails.entyGid = entyGid;
    }

    public static String getEmpName() {
        return empName;
    }
}
