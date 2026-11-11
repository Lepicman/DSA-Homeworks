public class HW1_isDuplicate {
    public static boolean isDup(String s) {
        if (s.length() % 2 == 1)
            return false;
        if (s.isEmpty())
            return true;

        String s1 = s.substring(0, s.length() / 2);
        String s2 = s.substring(s.length() / 2);
        if (s1.equals(s2))
            return isDup(s1.substring(1));
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isDup("ALLALL"));
        System.out.println(isDup("HTMMTMMHTMMTMM"));
        System.out.println(isDup("ALILIALILI"));
        System.out.println(isDup("HPOPOHPOPO"));
        System.out.println(isDup("POP"));
    }
}