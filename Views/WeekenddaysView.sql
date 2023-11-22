Create VIEW [dbo].[WeekenddaysView] AS
SELECT
  [id],
  [count_checked_in_customer],
  [day],
  [day_type],
  [local_date],
  [max_checkins_allowed],
  [month],
  [time],
  [time_of_day],
  [week],
  [OccupationRatio]
FROM [dbo].[keauit00_occupied]
WHERE [day_type] IN ('WEEKEND');
Select * from WeekenddaysView;