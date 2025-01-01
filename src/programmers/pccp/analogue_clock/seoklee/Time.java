package programmers.pccp.analogue_clock.seoklee;


public class Time {
    public int h = 0;
    public int m = 0;
    public int s = 0;
    public boolean isPm;

    public int mcnt = 0;
    public int hcnt = 0;

    public Time (int h, int m, int s, boolean isPm) {
        this.h = h;
        this.m = m;
        this.s = s;
        this.mcnt = s;
        this.hcnt = m * 60 + s;
        this.isPm = isPm;
    }

    public String toString() {
        return h + "시 " + m + "분 " + s + "초 ";
    }

    public Angles getAngles() {
        return new Angles(getAngleH(), getAngleM(), getAngleS());
    }

    public Angles getAfterAngles() {
        return new Angles(getAngleH() == 0 ? 360 : getAngleH()
            , getAngleM() == 0 ? 360 : getAngleM()
            , getAngleS()== 0 ? 360 : getAngleS()
        );
    }

    public double getAngleH() {
        return (double) (this.h * 30) + ((double) this.hcnt * (1.0 / 120));
    }

    public double getAngleM() {
        return (double) (this.m * 6) + ((double) this.mcnt * (1.0 / 10));
    }

    public double getAngleS() {
        return this.s * 6 ;
    }


    public Time next() {
        this.s++;
        this.mcnt++;
        this.hcnt++;

        if(this.mcnt == 60) {
            m++;
            this.mcnt = 0;
        }
        if(this.hcnt == 3600) {
            h++;
            this.hcnt = 0;
        }

        if (s == 60) {
            s = 0;
        }
        if (m == 60) {
            m = 0;
        }
        if (h == 12) {
            h = 0;
            this.isPm = true;
        }
        return this;
    }

    @Override
    public boolean equals(Object o) {
        Time other = (Time) o;
        return this.s == other.s
            && this.m == other.m
            && this.h == other.h
            && this.isPm == other.isPm;
    }

}
