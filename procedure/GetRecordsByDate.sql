CREATE PROCEDURE [dbo].[GetRecordsByDate]
    @selectedDate DATE
AS
BEGIN
    SET NOCOUNT ON;

    SELECT *
    FROM keauit00_occupied
    WHERE [local_date] = @selectedDate
    ORDER BY [time];
END;

