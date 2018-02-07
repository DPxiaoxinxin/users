import core.IndexManagement;
import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TeseCase {

    private IndexManagement indexManagement = new IndexManagement();
    private List<String> userIdList = new ArrayList<String>();
    private File file = new File("id.csv");

    public void init() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.file)));
            Random random = new Random(1000000000000L);
            for(int i = 0; i <= 1000; i++) {
                User user = new User();
                String id = this.buildRandomId();
                userIdList.add(id);
                user.setId(id);
                user.setDeposit(random.nextDouble());
                user.setAge(2018 - Integer.valueOf(user.getId().substring(7, 11)));
                indexManagement.putUser(user);
                bufferedWriter.append(id).append('\r');
            }
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void check() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(this.file)));
            Random random = new Random();
            int readNums = random.nextInt(2000);
            int curNums = 0;
            String id;
            long beginTime = System.currentTimeMillis();
            while (curNums <= readNums){
                while ((id = bufferedReader.readLine()) != null && random.nextBoolean()) {
                    curNums++;
                    User user = indexManagement.getUser(id);
                    System.out.println("" + curNums + ":" + user.getId());
                    assert user.getId().equals(id) : "id doesn't match";
                }
            }
//        for (String id : userIdList) {
//            User user = indexManagement.getUser(id);
//            System.out.println(user.getId());
//            assert user.getId().equals(id) : "id doesn't match";
//        }
            double intervalTime = (System.currentTimeMillis() - beginTime);
            System.out.println("Total Time is " + intervalTime);
            System.out.println("Every Time is" + intervalTime/readNums);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
        num = this.addZero(num, 2);
        String date = String.valueOf(1900 + random.nextInt(200));
        String mon = String.valueOf(random.nextInt(12));
        mon = this.addZero(mon, 2);
        date += mon;
        String day = String.valueOf(random.nextInt(31));
        day = this.addZero(day, 2);
        date += day;
        String sex = String.valueOf(random.nextInt(1));
        int next = random.nextInt(10);
        String validate = String.valueOf(next);
        if (next == 10) {
            validate = "x";
        }
        String id = area + date + num + sex + validate;
        assert id.length() == 18 : area + "," + date + "," + num + "," + sex + "," + validate;
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
        teseCase.check();
    }

}
