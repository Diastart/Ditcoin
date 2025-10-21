public class Timer extends Thread{
    int delay;
    public Timer(String name,int delay){
        super();
        setName(name);
        this.delay = delay;
    }
    @Override
    public void run() {
        int initialDelay = this.delay;
        while (true){
            this.delay--;
            try {
                sleep(1000);
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
            if(this.delay==-1){
                this.delay = initialDelay;
            }
        }
    }
}
