package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.DbBirdRecord;
import com.hirundo.libs.data_structures.NewDbBirdRecord;
import com.hirundo.libs.data_structures.OldDbBirdRecord;
import com.hirundo.libs.data_structures.Season;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReturningBirdsFinderTest {
ReturningBirdsSummarizer finder;
    @BeforeEach
    void setUp() {
        finder = new ReturningBirdsSummarizer();
    }

    @Test
    public void returnsCountDifferentSeasonTest() {
        var record1 = new NewDbBirdRecord();
        record1.SpeciesCode = "XXX.YYY";
        record1.Ring = "1234";
        record1.Date2 = "2019-11-01";
        record1.Seas = "A";
        record1.Age ="I";

        var r1 = DbBirdRecord.from(record1);

        var record2 = new OldDbBirdRecord();
        record2.Spec = "XXX.YYY";
        record2.Ring = "1234";
        record2.Date = LocalDateTime.of(2020, 3, 1, 0, 0);
        record2.Seas = "S";
        record2.Age ="I";
        var r2 = DbBirdRecord.from(record2);

        var record3 = new OldDbBirdRecord();
        record3.Spec = "XXX.YYY";
        record3.Age ="I";
        var r3 = DbBirdRecord.from(record3);

        var birds =  List.of(r1, r2, r3);

        var returningBirds = finder.getSummary(birds);

        assertEquals(1, returningBirds.size());
        assertEquals(2, returningBirds.get(0).Records.size());
        assertEquals("1234", returningBirds.get(0).RingNumber);
        assertEquals("XXX.YYY", returningBirds.get(0).Species);
        assertEquals(LocalDateTime.of(2019,11,1,0,0), returningBirds.get(0).FirstDateSeen);
        assertEquals(Season.Autumn, returningBirds.get(0).FirstSeasonSeen);
        assertEquals(LocalDateTime.of(2020,3,1,0,0), returningBirds.get(0).LastDateSeen);
        assertEquals(Season.Spring, returningBirds.get(0).LastSeasonSeen);
    }
}