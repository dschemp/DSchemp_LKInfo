package recursion.towerofhanoi;

public class TowerOfHanoi_Main {

    public static void main(String[] args) {
	    // write your code here
        Turm turmA = new Turm('A'), turmB = new Turm('B'), turmC = new Turm('C');
        // turmA.scheiben.push("10");
        turmA.scheiben.push("9");
        turmA.scheiben.push("8");
        turmA.scheiben.push("7");
        turmA.scheiben.push("6");
        turmA.scheiben.push("5");
        turmA.scheiben.push("4");
        turmA.scheiben.push("3");
        turmA.scheiben.push("2");
        turmA.scheiben.push("1");
        Loesen(turmA.scheiben.size(), turmA, turmB, turmC);
    }

    public static void Loesen(int n, Turm startTurm, Turm zwischenTurm, Turm endTurm) {
        if (n >= 1) {
            Loesen(n-1, startTurm, endTurm, zwischenTurm);
            String scheibe = startTurm.scheiben.pop();
            endTurm.scheiben.push(scheibe);
            System.out.println("[" + scheibe + "] " + startTurm.TurmName + " --> " + endTurm.TurmName);
            Loesen(n-1, zwischenTurm, startTurm, endTurm);
        }
    }
}
