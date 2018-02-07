import core.IndexManagement;
import model.User;

import java.util.Random;

public class TeseCase {

    private IndexManagement indexManagement = new IndexManagement();

    public void init() {
        Random random = new Random(1000000000000L);
        for(int i = 0; i <= 10; i++) {
            User user = new User();
            user.setId(this.buildRandomId());
            user.setDeposit(random.nextDouble());
            user.setAge(2018 - Integer.valueOf(user.getId().substring(7, 11)));
            indexManagement.putUser(user);
        }
    }

    public String buildRandomId() {
        Random random = new Random();
        String area = "";
        for (int i = 0; i < 3; i++) {
            String tmp = String.valueOf(random.nextInt(99));
            tmp = this.addZero(tmp, 2);
            area += tmp;
        }
        String num = String.valueOf(random.nextInt(99));
        String date = String.valueOf(1900 + random.nextInt(200));
        String mon = String.valueOf(random.nextInt(12));
        mon = this.addZero(mon, 2);
        date += mon;
        String day = String.valueOf(random.nextInt(31));
        day = this.addZero(day, 2);
        date += day;
        String sex = String.valueOf(random.nextInt(1));
        int next = random.nextInt(11);
        String validate = String.valueOf(next);
        if (next == 11) {
            validate = "x";
        }
        String id = area + date + num + sex + validate;
        return id;
    }

    private String addZero(String str, int length) {
        while (str.length() < length) {
            str = "0" + length;
        }
        return str;
    }

    public static void main(String[] args) {
        TeseCase teseCase = new TeseCase();
        teseCase.init();
    }

}
