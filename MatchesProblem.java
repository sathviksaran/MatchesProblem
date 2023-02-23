import java.util.*;
import java.util.stream.Collectors;
public class MatchesProblem
{
    public static void main(String[] args) throws Exception {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter no.of teams: ");
        int n=sc.nextInt();
        ArrayList<Teams> teams=new ArrayList<>();
        System.out.println("Enter Teams: ");
        for(int i=0;i<n;i++)
        {
            teams.add(new Teams(sc.next()));
        }
        List<Matches> matches=ScheduleMatch.Schedule(teams);
        System.out.println(matches);
        Result.conductMatches(matches);
        Result.displayPointsTable(teams,matches);
    }
}
class Matches
{
    private Teams team1;
    private Teams team2;
    private Teams winner;
    private Teams loser;
    Matches(Teams team,Teams team3)
    {
        this.team1=team;
        this.team2=team3;
    }
    public Teams getTeam1()
    {
        return team1;
    }
    public Teams getTeam2()
    {
        return team2;
    }
    public Teams getWinner()
    {
        return winner;
    }
    public void setWinner(Teams winner)
    {
        this.winner=winner;
    }
    public Teams getLoser()
    {
        return loser;
    }
    public void setLoser(Teams loser)
    {
        this.loser=loser;
    }
    @Override
    public String toString()
    {
        String matchDescription = team1 +" vs "+team2;
        if(winner!=null)
        {
            String result="\n winner="+this.winner.toString()+"Loser="+this.loser.toString();
            matchDescription+=result;
        }
        return matchDescription;
    }
}
class ScheduleMatch
{
    public static List<Matches> Schedule(List<Teams> teams)
    {
        List<Matches> matches=new ArrayList<>();
        for(int i=0;i<teams.size();i++)
        {
            for(int j=i+1;j<teams.size();j++)
            {
                Matches match=new Matches(teams.get(i), teams.get(j));
                matches.add(match);
            }
        }
        return matches;
    }
}
class Result
{
    /**
     * @param matches
     */
    public static void conductMatches(List<Matches> matches)
    {
        for(Matches match:matches)
        {
            int random=(int)((Math.random()*10)+1);
            if(random%2==0)
            {
                match.setWinner(match.getTeam1());
                match.setLoser(match.getTeam2());
            }
            else
            {
                match.setWinner(match.getTeam2());
                match.setLoser(match.getTeam1());
            }
        }
    }
    public static void displayPointsTable(List<Teams> teams,List<Matches> matches)
    {
        for(Teams team:teams)
        {
            int wonGames=matches.stream().filter(m ->m.getWinner().equals(team)).collect(Collectors.toList()).size();
            int lostGames=matches.stream().filter(m ->m.getLoser().equals(team)).collect(Collectors.toList()).size();
            System.out.println(team);
            System.out.println(wonGames);
            System.out.println(lostGames);
        }
    }
}
class Teams
{
    private String name;
    Teams(String name)
    {
        this.name=name;
    }
    @Override
    public String toString()
    {
        return name;
    }
    public boolean equals(Object obj)
    {
        return this.name.equals(((Teams)obj).name);
    }
}
