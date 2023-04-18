
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.bee.models.Team;
import com.bee.repository.TeamRepo;
import com.bee.service.TeamService;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TeamServiceTest {

    @Mock
    private TeamRepo teamRepo;

    @InjectMocks
    private TeamService teamService;

    @Test
    public void shouldAddTeamToTeamRepo() {
        Team team = new Team("eee", "ppp");

//        when(teamRepo.save(team)).thenReturn(team);

//        when(teamService.addTeam(team)).thenCallRealMethod();
        teamService.addTeam(team);

        verify(teamRepo).save(team);
    }

    @Test(expected = Exception.class)
    public void shouldNotFindTeamByNameIfDoesNotExist() {
        Team team = new Team("eee", "ppp");

        when(teamRepo.findTeamById(anyLong())).thenReturn(null);

//        when(teamService.addTeam(team)).thenCallRealMethod();
        teamService.findTeamById(128L);

    }

    @Test(expected = Exception.class)
    public void shouldNotFindTeamByNameIfIdDoesNotExist() {
        Team team = new Team("eee", "ppp");

//        when(teamRepo.findTeamById(team.getId())).thenReturn(Optional.of(team));
        when(teamRepo.findTeamById(team.getId() + 1)).thenReturn(null);

//        when(teamService.addTeam(team)).thenCallRealMethod();
        teamService.findTeamById(team.getId() + 1);

    }

    @Test()
    public void shouldNotFindTeamByNameIfIdDoesExist() {
        Team team = new Team("eee", "ppp");

        when(teamRepo.findTeamById(team.getId())).thenReturn(Optional.of(team));
//        when(teamRepo.findTeamById(team.getId()+1)).thenReturn(null);

//        when(teamService.addTeam(team)).thenCallRealMethod();
        Team team2 = teamService.findTeamById(team.getId());
        assertEquals(team, team2);
    }

    @Test
    public void shouldDeleteTeamFromTeamRepo() {
        Team team = new Team("eee", "ppp");

//        when(teamRepo.save(team)).thenReturn(team);

//        when(teamService.addTeam(team)).thenCallRealMethod();
        teamService.deleteTeam(team);

        verify(teamRepo).delete(team);
    }

    @Test
    public void shouldReturnTeamNameFromTeamRepo() {
        Team team = new Team("eee", "ppp");
        assertNotNull(team);
        teamService.addTeam(team);
        verify(teamRepo).save(team);
    }

    @Test()
    public void shouldNotFindTeamByWrongId() {
        Team team = new Team("eee", "ppp");
        Team team1 = new Team("eee1", "ppp");
        when(teamRepo.findTeamById(team.getId())).thenReturn(Optional.of(team));
//        when(teamRepo.findTeamById(team.getId()+1)).thenReturn(null);


//        when(teamService.addTeam(team)).thenCallRealMethod();
        assertNotEquals(team, team1);
    }
}