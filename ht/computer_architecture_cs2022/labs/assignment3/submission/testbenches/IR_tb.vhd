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

entity IR_tb is
--  Port ( );
end IR_tb;

architecture Behavioral of IR_tb is

    -- declare component to test
    component IR is
        Port ( 
            IR_IN : in std_logic_vector(15 downto 0);
            IL : in std_logic;
            CLK : in std_logic;
            OPCODE : out std_logic_vector(6 downto 0);
            DR : out std_logic_vector(2 downto 0);
            SA : out std_logic_vector(2 downto 0);
            SB : out std_logic_vector(2 downto 0)
        );
    end component;
    
    -- signals for tests (initialise to 0)
    
    --inputs    
    signal IR_IN : std_logic_vector(15 downto 0):= x"0000";
    signal IL : std_logic := '0';
    signal CLK : std_logic := '0';
    
    --outputs
    signal OPCODE : std_logic_vector(6 downto 0):= "0000000";
    signal DR : std_logic_vector(2 downto 0):= "000";
    signal SA : std_logic_vector(2 downto 0):= "000";
    signal SB : std_logic_vector(2 downto 0):= "000";        

begin

    -- instantiate component for test, connect ports to internal signals
    UUT: IR
    Port Map(
        IR_IN => IR_IN,
        IL => IL,
        CLK => CLK,
        OPCODE => OPCODE,
        DR => DR,
        SA => SA,
        SB => SB
    );
    
simulation_process :process
begin
        
        --Test loading random IR_IN (0xFFAB)
        -----------------------------------
        --OPCODE = 1111 111 (7F)
        --DR = 110 (6)
        --SA = 101 (5)
        --SB = 011 (3)
        
        IR_IN <= x"FFAB";
        IL <= '1';
        CLK <= '1';
        wait for 10ns;
        
        CLK <= '0';
        wait for 10ns;
        
        --Test loading blank IR_IN (0x0000)
        -----------------------------------
        --OPCODE = 0000 000 
        --DR = 000
        --SA = 000
        --SB = 000
        
        IR_IN <= x"0000";
        IL <= '1';
        CLK <= '1';
        wait for 10ns;   
        
        CLK <= '0';
        wait for 10ns;     
           
        --Test IL low (IL = 0) Shouldn't change outputs
        -----------------------------------
        --OPCODE = 0000 000 
        --DR = 000
        --SA = 000
        --SB = 000
        
        IR_IN <= x"FCDA";
        IL <= '0';
        CLK <= '1';
        wait for 1ns;   
        
        CLK <= '0';
        wait for 10ns;         
           
     end process;
    
end Behavioral;
