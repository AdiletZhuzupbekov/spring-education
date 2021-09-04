create table corona_stats
(
    id                    bigserial primary key,
    state                 varchar(255),
    country               varchar(255),
    latest_total_cases    int,
    diff_from_previousDay int
);