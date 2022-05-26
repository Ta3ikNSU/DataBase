create index announcement_price_idx_with_status on announcement(price) where status = 0;
create index brand_idx on car(brand, model) ;
create index engine_power_idx on car(engine_power);
create index engine_capacity_idx on car(engine_capacity);
create index mileage_idx on car(mileage);