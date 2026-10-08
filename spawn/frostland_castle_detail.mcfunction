# FrostLand Spawn — архитектурная версия
# Центр: 0 64 0 | Замок на севере (+Z)
#
# ВАЖНО: это blueprint-команды. Перед запуском проверить названия
# блоков и синтаксис fill для PNX 2.0.0.
#
# Цель: силуэт настоящего зимнего замка, а не прямоугольная коробка.

# === ПЛОЩАДЬ ===
fill -26 63 -26 26 63 26 stone_bricks
fill -24 64 -24 24 64 24 snow
fill -3 64 -26 3 64 8 stone_bricks
fill -2 65 -26 2 65 8 snow

# Круглая декоративная клумба/фонтан
fill -5 64 -5 5 64 5 stone_bricks
fill -3 65 -3 3 65 3 water
fill -1 66 -1 1 67 1 water
fill -1 68 -1 1 68 1 sea_lantern

# === ЗАМКОВЫЙ ДВОР ===
# Внешний периметр
fill -24 65 9 -21 76 31 stone_bricks
fill 21 65 9 24 76 31 stone_bricks
fill -21 65 28 21 76 31 stone_bricks
fill -21 65 9 21 76 12 stone_bricks

# Снежная кромка
fill -24 77 9 -21 77 31 snow
fill 21 77 9 24 77 31 snow
fill -21 77 28 21 77 31 snow
fill -21 77 9 21 77 12 snow

# === БАШНИ: НИЖНИЙ ЯРУС ===
fill -29 65 26 -21 84 34 stone_bricks
fill 21 65 26 29 84 34 stone_bricks
fill -29 65 6 -21 84 14 stone_bricks
fill 21 65 6 29 84 14 stone_bricks

# === БАШНИ: БАЛКОННЫЙ ЯРУС ===
fill -30 78 25 -20 79 35 dark_oak_planks
fill 20 78 25 30 79 35 dark_oak_planks
fill -30 78 5 -20 79 15 dark_oak_planks
fill 20 78 5 30 79 15 dark_oak_planks

# Верхние боевые площадки
fill -29 84 26 -21 85 34 stone_bricks
fill 21 84 26 29 85 34 stone_bricks
fill -29 84 6 -21 85 14 stone_bricks
fill 21 84 6 29 85 14 stone_bricks

# === КРЫШИ БАШЕН ===
# Слои пирамидальных крыш
fill -30 86 25 -20 86 35 dark_oak_planks
fill -29 87 26 -21 87 34 dark_oak_planks
fill -28 88 27 -22 88 33 dark_oak_planks
fill -27 89 28 -23 89 32 dark_oak_planks
fill -26 90 29 -24 90 31 dark_oak_planks
fill -25 91 30 -25 92 30 dark_oak_planks

fill 20 86 25 30 86 35 dark_oak_planks
fill 21 87 26 29 87 34 dark_oak_planks
fill 22 88 27 28 88 33 dark_oak_planks
fill 23 89 28 27 89 32 dark_oak_planks
fill 24 90 29 26 90 31 dark_oak_planks
fill 25 91 30 25 92 30 dark_oak_planks

fill -30 86 5 -20 86 15 dark_oak_planks
fill -29 87 6 -21 87 14 dark_oak_planks
fill -28 88 7 -22 88 13 dark_oak_planks
fill -27 89 8 -23 89 12 dark_oak_planks
fill -26 90 9 -24 90 11 dark_oak_planks
fill -25 91 10 -25 92 10 dark_oak_planks

fill 20 86 5 30 86 15 dark_oak_planks
fill 21 87 6 29 87 14 dark_oak_planks
fill 22 88 7 28 88 13 dark_oak_planks
fill 23 89 8 27 89 12 dark_oak_planks
fill 24 90 9 26 90 11 dark_oak_planks
fill 25 91 10 25 92 10 dark_oak_planks

# Снежные шапки крыш
fill -29 86 26 -21 86 34 snow
fill 21 86 26 29 86 34 snow
fill -29 86 6 -21 86 14 snow
fill 21 86 6 29 86 14 snow

# === ГЛАВНАЯ ВОРОТНАЯ БАШНЯ ===
fill -8 65 27 8 91 35 stone_bricks
fill -5 65 27 5 78 35 air
fill -4 65 27 4 74 35 dark_oak_planks
fill -10 89 25 10 92 37 dark_oak_planks
fill -8 92 27 8 93 35 snow
fill -6 94 29 6 94 33 snow

# Ворота — арочный силуэт
fill -7 72 27 7 77 35 stone_bricks
fill -5 72 27 5 75 35 dark_oak_planks
fill -4 73 27 4 75 35 air

# === БОЙНИЦЫ И ОКНА ===
fill -18 72 29 -15 74 31 glass
fill 15 72 29 18 74 31 glass
fill -18 72 10 -15 74 12 glass
fill 15 72 10 18 74 12 glass

fill -24 70 28 -22 72 29 air
fill 22 70 28 24 72 29 air
fill -24 70 12 -22 72 13 air
fill 22 70 12 24 72 13 air

# === ТРОННЫЙ ЗАЛ ===
fill -14 67 15 14 81 26 stone_bricks
fill -11 68 18 11 68 23 dark_oak_planks
fill -11 69 18 11 78 23 air
fill -4 68 15 4 75 15 air

# Задняя стена и тронная ниша
fill -5 69 23 5 78 25 stone_bricks
fill -3 70 24 3 75 25 dark_oak_planks
fill -1 71 24 1 73 24 gold_block
fill -2 70 25 2 70 25 dark_oak_planks

# === БОКОВЫЕ ГАЛЕРЕИ ===
fill -20 67 14 -15 79 26 stone_bricks
fill 15 67 14 20 79 26 stone_bricks
fill -18 68 16 -15 76 24 dark_oak_planks
fill 15 68 16 18 76 24 dark_oak_planks

# === СОСНЫ ===
# Маленькие декоративные деревья — ступенчатые силуэты
fill -18 65 -18 -18 69 -18 spruce_log
fill -20 67 -16 -16 67 -20 spruce_leaves
fill 18 65 -18 18 69 -18 spruce_log
fill 16 67 -20 20 67 -16 spruce_leaves
fill -18 65 18 -18 69 18 spruce_log
fill -20 67 16 -16 67 20 spruce_leaves
fill 18 65 18 18 69 18 spruce_log
fill 16 67 16 20 67 20 spruce_leaves

# === ФОНАРИ ===
fill -14 65 -14 -14 68 -14 spruce_fence
fill 14 65 -14 14 68 -14 spruce_fence
fill -14 65 14 -14 68 14 spruce_fence
fill 14 65 14 14 68 14 spruce_fence
fill -14 69 -14 -14 69 -14 lantern
fill 14 69 -14 14 69 -14 lantern
fill -14 69 14 -14 69 14 lantern
fill 14 69 14 14 69 14 lantern

# === СНЕГ И ЛЁД ===
fill -24 65 -24 -20 66 -20 snow
fill 20 65 -24 24 66 -20 snow
fill -24 65 -10 -21 67 -7 snow
fill 21 65 -10 24 67 -7 snow
fill -20 65 35 20 65 36 snow
fill -32 65 23 -30 67 26 snow
fill 30 65 23 32 67 26 snow

# === ПЛОЩАДКА СОБЫТИЙ ===
fill -18 65 -22 18 65 -12 stone_bricks
fill -16 66 -16 16 66 -14 snow
fill -2 67 -16 2 70 -14 ice
fill -14 67 -18 14 67 -18 spruce_fence

# === МЕСТА ПОД ПОРТАЛЫ/ИНФО ===
fill -23 65 -8 -18 69 -3 obsidian
fill -22 66 -19  -19 68 -19 air
fill 18 65 -8 23 69 -3 stone_bricks

# === ФИНАЛЬНЫЙ ДЕКОР ===
fill -12 78 30 -9 79 30 snow
fill 9 78 30 12 79 30 snow
fill -3 82 34 3 83 34 snow
fill -3 90 34 3 91 34 snow

# TODO: проверить block IDs PNX 2.0.0 и при необходимости заменить
# water/ice/lantern/glass/spruce_* на совместимые идентификаторы.
