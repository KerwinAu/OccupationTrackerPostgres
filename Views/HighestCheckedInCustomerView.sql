CREATE VIEW [dbo].[HighestCheckedInCustomerView] AS
SELECT TOP 1
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
ORDER BY [count_checked_in_customer] DESC;
select * from HighestCheckedInCustomerView;