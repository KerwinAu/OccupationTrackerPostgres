-- Ändern Sie den vorhandenen AFTER INSERT Trigger
Create TRIGGER CalculatePercentageTrigger
ON keauit00_Occupied
AFTER INSERT
AS
BEGIN
    -- Update the OccupationRatio column with the calculated percentage
    UPDATE keauit00_Occupied
    SET occupation_ratio = (CAST(i.Count_Checked_In_customer AS DECIMAL(5, 2)) / CAST(i.Max_Checkins_allowed AS DECIMAL(5, 2)) * 100)
    FROM inserted i
    WHERE keauit00_Occupied.id = i.id;
END;
