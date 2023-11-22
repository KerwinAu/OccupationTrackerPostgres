create PROCEDURE [dbo].[GetMonthlyRecords]
    @selectedMonth NVARCHAR(20)
AS
BEGIN
    SET NOCOUNT ON;

    SELECT *
    FROM keauit00_occupied
    WHERE [month] = @selectedMonth
    ORDER BY [local_date], [time];
END;




