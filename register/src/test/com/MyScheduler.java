import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.scheduler.Scheduler;

/**
 * Created by chenxidu on 7/20/16.
 * 去掉重复的url
 */
public class MyScheduler implements Scheduler {

    @Override
    public void push(Request request, Task task) {
        System.out.println(request.toString());

    }

    @Override
    public Request poll(Task task) {
        System.out.println(task.toString());
        return null;
    }
}
