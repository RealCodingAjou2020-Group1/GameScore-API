package org.ajou.realcoding.lolapi.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Map;

@Data
public class MatchData {
    @Id
    private long gameId;
    private List<ParticipantIdentityDto> participantIdentities;
    private int queueId;
    private String gameType;
    private long gameDuration;
    private List<TeamStatsDto> teams;
    private String platformId;
    private long gameCreation;
    private int seasonId;
    private String gameVision;
    private int mapId;
    private String gameMode;
    private List<ParticipantDto> participants;

    @Data
    public static class ParticipantIdentityDto{
        private int participantId;
        private PlayerDto player;
    }

    @Data
    public static class PlayerDto{
        private int profileIcon;
        private String accountId;
        private String matchHistoryUri;
        private String currentAccountId;
        private String summonerName;
        private String summonerId;
        private String platformId;
    }

    @Data
    public static class TeamStatsDto{
        private int towerKills;
        private int riftHeraldKills;
        private boolean firstBlood;
        private int inhibitorKills;
        private List<TeamBansDto> bans;
        private boolean firstBaron;
        private boolean firstDragon;
        private int dominionVictoryScore;
        private int dragonKills;
        private int baronKills;
        private boolean firstInhibitor;
        private boolean firstTower;
        private int vilemawKills;
        private boolean firstRiftHerald;
        private int teamId;
        private String win;
    }

    @Data
    public static class TeamBansDto{
        private int championId;
        private int pickTurn;
    }

    @Data
    public static class ParticipantDto{
        private int participantId;
        private int championId;
        private List<RuneDto> runes;
        private ParticipantStatsDto stats;
        private int teamId;
        private ParticipantTimelineDto timeline;
        private int spell1Id;
        private int spell2Id;
        private String highestAchievedSeasonTier;
        private List<MasteryDto> masteries;
    }

    @Data
    public static class RuneDto{
        private int runeId;
        private int rank;
    }

    @Data
    public static class ParticipantStatsDto{
        private int item0;
        private int item2;
        private int totalUnitsHealed;
        private int item1;
        private int largestMultiKill;
        private int goldEarned;
        private boolean firstInhibitorKill;
        private long physicalDamageTaken;
        private int nodeNeutralizeAssist;
        private int totalPlayerScore;
        private int champLevel;
        private long damageDealtToObjectives;
        private long totalDamageTaken;
        private int neutralMinionsKilled;
        private int deaths;
        private int tripleKills;
        private long magicDamageDealtToChampions;
        private int wardsKilled;
        private int pentaKills;
        private long damageSelfMitigated;
        private int largestCitiesStrike;
        private int nodeNeutralize;
        private int totalTimeCrowdControlDealt;
        private boolean firstTowerKill;
        private long magicDamageDealt;
        private int totalScoreRank;
        private int nodeCapture;
        private int wardsPlaced;
        private long totalDamageDealt;
        private long timeCCingOthers;
        private long magicDamageTaken;
        private int largestKillingSpree;
        private long totalDamageDealtToChampions;
        private long physicalDamageDealtToChampions;
        private int neutralMinionsKilledTeamJungle;
        private int totalMinionsKilled;
        private boolean firstInhibitorAssist;
        private int visionWardsBoughtGame;
        private int objectivePlayerScore;
        private int kills;
        private boolean firstTowerAssist;
        private int combatPlayerScore;
        private int inhibitorKills;
        private int turretKills;
        private int participantId;
        private long trueDamageTaken;
        private boolean firstBloodAssist;
        private int nodeCaptureAssist;
        private int assists;
        private int teamObjective;
        private int altarsNeutralized;
        private int goldSpent;
        private long damageDealtToTurrets;
        private int altarsCaptured;
        private boolean win;
        private long totalHeal;
        private int unrealKills;
        private long visionScore;
        private long physicalDamageDealt;
        private boolean firstBloodKill;
        private int longestTimeSpentLiving;
        private int killingSprees;
        private int sightWardsBoughtInGame;
        private long trueDamageDealtToChampions;
        private int neutralMinionsKilledEnemyJungle;
        private int doubleKills;
        private long trueDamageDealt;
        private int quadraKills;
        private int item4;
        private int item3;
        private int item6;
        private int item5;
        private int playerScore0;
        private int playerScore1;
        private int playerScore2;
        private int playerScore3;
        private int playerScore4;
        private int playerScore5;
        private int playerScore6;
        private int playerScore7;
        private int playerScore8;
        private int playerScore9;
        private int perk0;
        private int perk0Var1;
        private int perk0Var2;
        private int perk0Var3;
        private int perk1;
        private int perk1Var1;
        private int perk1Var2;
        private int perk1Var3;
        private int perk2;
        private int perk2Var1;
        private int perk2Var2;
        private int perk2Var3;
        private int perk3;
        private int perk3Var1;
        private int perk3Var2;
        private int perk3Var3;
        private int perk4;
        private int perk4Var1;
        private int perk4Var2;
        private int perk4Var3;
        private int perk5;
        private int perk5Var1;
        private int perk5Var2;
        private int perk5Var3;
        private int perkPrimaryStyle;
        private int perkSubStyle;
    }

    @Data
    public static class ParticipantTimelineDto{
        private int participantId;
        private Map<String, Double> csDiffPerMinDeltas;
        private Map<String, Double> damageTakenPerMinDeltas;
        private String role;
        private Map<String, Double> damageTakenDiffPerMinDeltas;
        private Map<String, Double> xpPerMinDeltas;
        private Map<String, Double> xpDiffPerMinDeltas;
        private String lane;
        private Map<String, Double> creepsPerMinDeltas;
        private Map<String, Double> goldPerMinDeltas;
    }

    @Data
    public static class MasteryDto{
        private int rank;
        private int masteryId;
    }

}
