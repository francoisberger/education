# counting-card-game-generator
A generator for card games used to read and pronounce numbers at primary school.

Basically a game for X players is a set of X cards. Any player starts and says "I have number XXX who has number YYY" ?" The player who as the corresponding card goes on... until the very first player is called again (meaning all cards have been read and we're in a loop).

A single game provides several difficulties (several card sets):
- an easy card set contains only easy numbers (for instance numbers where every digit is pronounced) and no number like 70, 80 or 90 which are quite hard to read in french.
- a medium card set is basically an EASY card set with a few 80.
- a hard card set contains any number plus some 70, 80, 90 and numbers with two consecutives 0 like 1006 where every digit is not pronounced.
- a harder card set is an hard card set with even more complex numbers

Generated games are meant to be saved to CSV, then used in conjonction with mailing possibilities of libreoffice to generate an actual physical game.

Please see an example at https://maitrefrancois.com/2021/04/07/furet-des-nombres/


