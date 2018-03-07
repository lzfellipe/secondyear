----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 02/14/2018 12:22:16 PM
-- Design Name: 
-- Module Name: mux2_16bit_tb - Behavioral
-- Project Name: 
-- Target Devices: 
-- Tool Versions: 
-- Description: 
-- 
-- Dependencies: 
-- 
-- Revision:
-- Revision 0.01 - File Created
-- Additional Comments:
-- 
----------------------------------------------------------------------------------


library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity mux2_1bit_tb is
--  Port ( );
end mux2_1bit_tb;

architecture Behavioral of mux2_1bit_tb is
    -- declare component to test
    component mux2_1bit is
        Port(
                Sel, In0, In1 : in STD_LOGIC;
                mux_out : out STD_LOGIC
            );
    end component;
    
    -- signals for tests (initialise to 0)
        
        signal Sel : std_logic := '0';
        signal In0 : std_logic := '0';
        signal In1 : std_logic := '0';
        signal mux_out : std_logic := '0';
        
begin

    -- instantiate component for test, connect ports to internal signals
    UUT: mux2_1bit
    Port map(
        Sel => Sel,
        In0 => In0,
        In1 => In1,
        mux_out => mux_out
    );
    
    simulation_process :process
    begin
        
        In0 <= '0';
        In1 <= '1';
        
        --Select line 1 and send '1' to output line mux_out
        Sel <= '0';
        wait for 5ns;
        
        --Select line 0 and send '0' to output line mux_out
        Sel <= '1';
        wait for 5ns;
     
     end process;
    
end Behavioral;