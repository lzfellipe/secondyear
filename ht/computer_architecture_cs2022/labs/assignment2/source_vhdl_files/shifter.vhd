library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity shifter_mux is
port ( In0 : in std_logic;
In1 : in std_logic;
In2 : in std_logic;
s : in std_logic_vector(1 downto 0);
Z : out std_logic);
end shifter_mux;

architecture Behavioral of shifter_mux is
begin
Z <= In0 when S='00' else
In1 when S='01'else
In2 when S='10';
end Behavioral;
