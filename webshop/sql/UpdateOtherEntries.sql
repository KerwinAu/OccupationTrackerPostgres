CREATE TRIGGER UpdateOtherEntries
ON keauit00_occupied
AFTER UPDATE
AS
BEGIN
    SET NOCOUNT ON;

    -- Check if [max_checkins_allowed] was updated
    IF UPDATE(max_checkins_allowed)
    BEGIN
        -- Declare variables
        DECLARE @newMaxCheckinsAllowed INT;
        DECLARE @id INT;

        -- Declare a cursor to loop through updated rows
        DECLARE cursorUpdatedRows CURSOR FOR
        SELECT id, max_checkins_allowed
        FROM inserted;

        -- Open the cursor
        OPEN cursorUpdatedRows;

        -- Fetch the first row from the cursor
        FETCH NEXT FROM cursorUpdatedRows INTO @id, @newMaxCheckinsAllowed;

        -- Loop through all updated rows
        WHILE @@FETCH_STATUS = 0
        BEGIN
            -- Update all entries with the new max_checkins_allowed value
            UPDATE keauit00_occupied
            SET max_checkins_allowed = @newMaxCheckinsAllowed
            WHERE id = @id;

            -- Fetch the next row from the cursor
            FETCH NEXT FROM cursorUpdatedRows INTO @id, @newMaxCheckinsAllowed;
        END;

        -- Close and deallocate the cursor
        CLOSE cursorUpdatedRows;
        DEALLOCATE cursorUpdatedRows;
    END
END;
