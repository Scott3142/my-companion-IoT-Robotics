#!/usr/bin/env python3
import roslib
import sys
import unittest

class TestBareBones(unittest.TestCase):
    def test_one_equals_one(self):
        self.assertEquals(1, 1, "1!=1")

if __name__ == "__main__":
    import rostest
    rostest.rosrun('my_companion', 'test_bare_bones', TestBareBones)
