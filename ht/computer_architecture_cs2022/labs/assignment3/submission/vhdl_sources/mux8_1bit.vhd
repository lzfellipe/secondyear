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

entity mux8_1bit is
	Port (
		In0 : in std_logic;
		In1 : in std_logic;
		In2 : in std_logic;
		In3 : in std_logic; 
		In4 : in std_logic; 
		In5 : in std_logic; 
		In6 : in std_logic; 
		In7 : in std_logic;
		S0 : in std_logic;
		S1 : in std_logic;
		S2 : in std_logic;
		Z : out std_logic
	);
end mux8_1bit;

architecture Behavioral of mux8_1bit is
begin
	Z <= In0 after 5 ns when S0='0' and S1='0' and S2 = '0' else
        In1 after 5 ns when S0='1' and S1='0' and S2 = '0'  else
        In2 after 5 ns when S0='0' and S1='1' and S2 = '0'  else
        In3 after 5 ns when S0='1' and S1='1' and S2 = '0'  else
        In4 after 5 ns when S0='0' and S1='0' and S2 = '1'  else
        In5 after 5 ns when S0='1' and S1='0' and S2 = '1'  else
        In6 after 5 ns when S0='0' and S1='1' and S2 = '1'  else
        In7 after 5 ns when S0='1' and S1='1' and S2 = '1'  else
        '0' after 5 ns;
end Behavioral;