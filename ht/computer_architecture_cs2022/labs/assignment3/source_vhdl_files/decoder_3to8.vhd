-- MIT License
--
-- Copyright (c) 2017 Brandon Dooley - dooleyb1@tcd.ie
--
-- Permission is hereby granted, free of charge, to any person obtaining a copy
-- of this software and associated documentation files (the "Software"), to deal
-- in the Software without restriction, including without limitation the rights
-- to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
-- copies of the Software, and to permit persons to whom the Software is
-- furnished to do so, subject to the following conditions:
--
-- The above copyright notice and this permission notice shall be included in
-- all copies or substantial portions of the Software.
--
-- THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
-- IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
-- FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
-- AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
-- LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
-- OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
-- THE SOFTWARE.
--

library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity decoder_3to8 is
	Port ( 
		des: in std_logic_vector(2 downto 0);
		Q0 : out std_logic;
		Q1 : out std_logic;
		Q2 : out std_logic;
		Q3 : out std_logic;
		Q4 : out std_logic;
		Q5 : out std_logic;
		Q6 : out std_logic;
		Q7 : out std_logic
	);
end decoder_3to8;

architecture Behavioral of decoder_3to8 is
begin
	Q0<= '1' after 1ns when des = "000" else '0' after 1ns;
	Q1<= '1' after 1ns when des = "001" else '0' after 1ns;
	Q2<= '1' after 1ns when des = "010" else '0' after 1ns;
	Q3<= '1' after 1ns when des = "011" else '0' after 1ns;
	Q4<= '1' after 1ns when des = "100" else '0' after 1ns;
	Q5<= '1' after 1ns when des = "101" else '0' after 1ns;
	Q6<= '1' after 1ns when des = "110" else '0' after 1ns;
	Q7<= '1' after 1ns when des = "111" else '0' after 1ns;
end Behavioral;
