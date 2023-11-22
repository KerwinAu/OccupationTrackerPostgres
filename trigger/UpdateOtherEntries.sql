Create TRIGGER UpdateOtherEntries
ON [dbo].[keauit00_occupied]
AFTER UPDATE
AS
BEGIN
    -- Check if [max_checkins_allowed] was updated
    IF UPDATE([max_checkins_allowed])
    BEGIN
        -- Get the updated [max_checkins_allowed] value
        DECLARE @newMaxCheckinsAllowed INT;
        SELECT @newMaxCheckinsAllowed = [max_checkins_allowed]
        FROM inserted;

        -- Update all entries
        UPDATE [dbo].[keauit00_occupied]
        SET
            [max_checkins_allowed] = @newMaxCheckinsAllowed;
    END
END;

